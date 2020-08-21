package dy.network.hundred.service.impl;


import dy.network.hundred.dao.IntegralDao;
import dy.network.hundred.dao.PayrollDao;
import dy.network.hundred.dao.UserDao;
import dy.network.hundred.java_bean.IntegralBean;
import dy.network.hundred.java_bean.PayrollBean;
import dy.network.hundred.java_bean.UserBean;
import dy.network.hundred.service.IntegraService;
import dy.network.hundred.java_bean.BaseBean;
import dy.network.hundred.utils.IntegerUtil;
import dy.network.hundred.utils.MD5Utils;
import dy.network.hundred.utils.PayrollUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("integralService")
public class IntegralServiceImpl implements IntegraService {
    private static final Logger log = LoggerFactory.getLogger(IntegralServiceImpl.class);


    @Autowired
    private IntegralDao integralDao;

    @Autowired
    private PayrollDao payrollDao;

    @Autowired
    private UserDao userDao;


    @Transactional(propagation = Propagation.REQUIRED)
    public BaseBean TransferToEachOther(IntegralBean integralBean) {
        BaseBean baseBean = new BaseBean();

        UserBean property = userDao.findUserDataByUserId(integralBean.getUser_id());
        if (property == null) {
            baseBean.setMsg("用户不存在");
            return baseBean;
        }

        UserBean targetUser = userDao.findUserDataByUserId(integralBean.getTarget_user_id());
        if (targetUser == null) {
            baseBean.setMsg("目标用户不存在");
            return baseBean;
        }

        if (integralBean.getUser_id() == integralBean.getTarget_user_id()) {
            baseBean.setMsg("不能给自己转账");
            return baseBean;
        }

        log.info(integralBean.getPay_password());
        property.setPay_password(MD5Utils.generateMD5(integralBean.getPay_password()));
        log.info(property.toString());
        log.info(property.getPay_password());
        Integer isTrue = userDao.payPasswordIsTrue(property);
        if (isTrue <= 0) {
            baseBean.setMsg("支付密码错误");
            return baseBean;
        }

        if (property.getIntegral_num() < integralBean.getTransaction_amount()) {
            baseBean.setMsg("你的积分不足");
            return baseBean;
        }

        boolean result = IntegerUtil.addIntegerRecord(IntegralBean.TRANSFERS_BETWEEN, -integralBean.getTransaction_amount(), integralBean.getUser_id(), integralBean.getTarget_user_id(), integralBean
                .getTransaction_remark(), userDao, integralDao);
        boolean result2 = IntegerUtil.addIntegerRecord(IntegralBean.TRANSFERS_BETWEEN, integralBean.getTransaction_amount(), integralBean.getTarget_user_id(), integralBean.getUser_id(), integralBean
                .getTransaction_remark(), userDao, integralDao);

        if (result && result2) {
            baseBean.setCode(BaseBean.SUCCESS);
            baseBean.setMsg("转账成功");
        } else {
            baseBean.setMsg("转账失败");
        }


        return baseBean;
    }


    public BaseBean<List<IntegralBean>> findIntegralDataByUid(Integer user_id) {

        Integer exists = userDao.getUserExists(user_id);

        BaseBean<List<IntegralBean>> baseBean = new BaseBean();

        if (exists != 0) {
            List<IntegralBean> integralBeans = integralDao.findIntegralDataByUid(user_id);
            if (integralBeans != null && integralBeans.size() != 0) {
                baseBean.setCode(BaseBean.SUCCESS);
                baseBean.setMsg("信息获取成功");
                baseBean.setData(integralBeans);
            } else {
                baseBean.setCode(BaseBean.SUCCESS);
                baseBean.setMsg("信息获取成功");
            }
        } else {

            baseBean.setMsg("用户不存在");
        }
        return baseBean;
    }


    public BaseBean<Long> findUserIntegralByUserID(Integer user_id) {
        Integer exists = userDao.getUserExists(user_id);
        BaseBean<Long> baseBean = new BaseBean();
        if (exists != 0) {
            UserBean property = userDao.getUserProperty(user_id);
            baseBean.setData(Long.valueOf(property.getIntegral_num()));
            baseBean.setCode(BaseBean.SUCCESS);
            baseBean.setMsg("信息获取成功");
        } else {

            baseBean.setMsg("用户不存在");
        }
        return baseBean;
    }


    public BaseBean<Long> integralToPayroll(IntegralBean integralBean) {
        BaseBean<Long> baseBean = new BaseBean();
        UserBean user = userDao.findUserDataByUserId(integralBean.getUser_id());
        if (user == null) {
            baseBean.setMsg("用户不存在");
            return baseBean;
        }
        user.setPay_password(MD5Utils.generateMD5(integralBean.getPay_password()));
        Integer isTrue = userDao.payPasswordIsTrue(user);

        if (isTrue <= 0) {
            baseBean.setMsg("支付密码错误");
            return baseBean;
        }

        int amount = (int) integralBean.getTransaction_amount();

        if (user.getIntegral_num() < amount) {
            baseBean.setMsg("积分不足");
            return baseBean;
        }

        IntegerUtil.addIntegerRecord(PayrollBean.CONVERSION, -amount, user.getUser_id(), null, integralBean
                .getTransaction_remark(), userDao, integralDao);

        PayrollUtil.addPayrollRecord(PayrollBean.CONVERSION, amount, user.getUser_id(), null, "", 0, integralBean
                .getTransaction_remark(), userDao, payrollDao);


        baseBean.setCode(BaseBean.SUCCESS);
        baseBean.setMsg("转换成功");

        return baseBean;
    }

}

