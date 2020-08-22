package dy.network.hundred.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import dy.network.hundred.dao.ConfigDao;
import dy.network.hundred.dao.DeMachineNumDao;
import dy.network.hundred.dao.IntegralDao;
import dy.network.hundred.dao.UserDao;
import dy.network.hundred.java_bean.*;
import dy.network.hundred.service.DeMachineService;
import dy.network.hundred.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.LinkedHashMap;

@Slf4j
@Service("deMachineService")
public class DeMachineServiceImpl implements DeMachineService {

    @Autowired
    private DeMachineNumDao deMachineNumDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private IntegralDao integralDao;

    @Autowired
    private ConfigDao configDao;

    @Override
    public BaseBean<Integer> getDemachineNum(Integer user_id) {
        BaseBean baseBean = new BaseBean();

        Integer num = deMachineNumDao.getDemachineNum(user_id);

        if (num == null) {
            baseBean.setMsg("获取失败");
            return baseBean;
        }

        baseBean.setCode(BaseBean.SUCCESS);
        baseBean.setMsg("获取成功");
        baseBean.setData(num);

        return baseBean;
    }

    @Transactional
    @Override
    public BaseBean<Integer> rechargeDemachineNum(UserBean user) {
        BaseBean baseBean = new BaseBean();

        user.setPay_password(MD5Utils.generateMD5(user.getPay_password()));
        Integer isTrue = userDao.payPasswordIsTrue(user);

        if (isTrue <= 0) {
            baseBean.setMsg("支付密码错误");
            return baseBean;
        }

        user = userDao.findUserDataByUserId(user.getUser_id());

        int demachine_recharge_num = ConfigUtil.getConfigValueInteger(ConfigBean.DEMACHINE_RECHARGE, configDao);

        if (user.getIntegral_num() < demachine_recharge_num) {
            baseBean.setMsg("积分不足");
            return baseBean;
        }

        DeMachineNumBean deMachineNumBean = deMachineNumDao.findDeMachineNumByUserId(user.getUser_id());
        if (deMachineNumBean == null) {
            deMachineNumBean = new DeMachineNumBean(0, user.getUser_id(), DateUtil.getDate());
            deMachineNumDao.addDeMachineNumData(deMachineNumBean);
        }

        deMachineNumBean.addDeMachineNum(100);//添加次数
        deMachineNumBean.setUpdate_time(DateUtil.getDate());

        deMachineNumDao.updateDeMachineNumBean(deMachineNumBean);//更新一下

        IntegerUtil.addIntegerRecord(IntegralBean.BUY_SET_MEAL, -demachine_recharge_num, user.getUser_id(), null,
                "理疗次数充值", userDao, integralDao);

        baseBean.setMsg("充值成功");
        baseBean.setCode(BaseBean.SUCCESS);

        return baseBean;
    }

    /**
     * 扫码启动机器
     *
     * @param userBean
     * @return
     */
    @Override
    @Transactional
    public BaseBean scanStartMachine(UserBean userBean) {

        BaseBean baseBean = new BaseBean();

        DeMachineNumBean deMachineNumBean = deMachineNumDao.findDeMachineNumByUserId(userBean.getUser_id());

        if (deMachineNumBean == null || deMachineNumBean.getDemachine_num() <= 0) {
            baseBean.setMsg("次数不足");
            return baseBean;
        }

        DemachineBean demachineBean = deMachineNumDao.findDeMachineBySn(userBean.getSn());

        log.info(demachineBean.toString());

        String account = ConfigUtil.getConfigValueString(ConfigBean.DEMACHINE_ACCOUNT, configDao);
        String password = ConfigUtil.getConfigValueString(ConfigBean.DEMACHINE_PASS, configDao);

        HashMap<String, String> hashMap = new LinkedHashMap<>();
        hashMap.put("username", account);
        hashMap.put("password", password);
        hashMap.put("randomcode", "");
        hashMap.put("randomNum", "");
        hashMap.put("flag", "2");

        RestClient.setRestTemplate(new RestTemplate());
        BaseBean loginResult = RestClient.postForm("https://api.huaruanzc.net/backend-api/loginController.do?login", hashMap, BaseBean.class);
        if (loginResult.getCode() != 0) {
            baseBean.setMsg("网络错误");
            return baseBean;
        }
        LinkedHashMap linkedHashMap = (LinkedHashMap) loginResult.getData();

        String token = (String) linkedHashMap.get("token");

        BaseBean startResult = RestClient.deviceStart("https://api.huaruanzc.net/backend-api/deviceManagerController.do?deviceStart"
                , token, demachineBean.getDeviceid(), demachineBean.getItemid(), BaseBean.class);

        log.info(startResult.toString());
        if (startResult.getCode() != 0) {
            baseBean.setMsg(startResult.getMsg());
            return baseBean;
        }

        deMachineNumBean.addDeMachineNum(-1);//减1
        deMachineNumDao.updateDeMachineNumBean(deMachineNumBean);//更新一下

        baseBean.setMsg("启动成功");
        baseBean.setCode(BaseBean.SUCCESS);//成功

        return baseBean;
    }


}
