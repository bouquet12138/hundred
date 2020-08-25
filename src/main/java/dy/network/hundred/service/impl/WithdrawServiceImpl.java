package dy.network.hundred.service.impl;

import dy.network.hundred.dao.*;
import dy.network.hundred.java_bean.*;
import dy.network.hundred.java_bean.db_bean.ConfigBean;
import dy.network.hundred.java_bean.db_bean.PayrollBean;
import dy.network.hundred.java_bean.db_bean.UserBean;
import dy.network.hundred.java_bean.db_bean.WithdrawBean;
import dy.network.hundred.service.WithdrawService;
import dy.network.hundred.utils.*;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static dy.network.hundred.java_bean.BaseBean.SUCCESS;

@Slf4j
@Service("withdrawService")
public class WithdrawServiceImpl implements WithdrawService {

    @Autowired
    private WithdrawDao withdrawDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private IntegralDao integralDao;
    @Autowired
    private PayrollDao payrollDao;
    @Autowired
    private ConfigDao configDao;


    @Transactional(propagation = Propagation.REQUIRED)
    public BaseBean<Integer> userWithdrawal(WithdrawBean withdrawBean) {
        BaseBean<Integer> baseBean = new BaseBean();

        if (!DateUtil.isInTime()) {
            baseBean.setMsg("不在提现时间范围内");
            return baseBean;
        }


        UserBean userBean = this.userDao.findUserDataByUserId(withdrawBean.getUser_id());
        if (userBean == null) {
            baseBean.setMsg("用户不存在");
            return baseBean;
        }

        if (userBean.getPayroll_num() < 100L) {
            baseBean.setMsg("工资满100才能提现");
            return baseBean;
        }

        userBean.setPay_password(MD5Utils.generateMD5(withdrawBean.getPay_password()));
        Integer isTrue = userDao.payPasswordIsTrue(userBean);
        if (isTrue.intValue() <= 0) {
            baseBean.setMsg("支付密码错误");
            return baseBean;
        }
        if (withdrawBean.getWithdraw_amount() > userBean.getPayroll_num()) {
            baseBean.setMsg("余额不足");
            return baseBean;
        }

        float service_ratio = 0.0F;
        float individual_income_ratio = 0.0F;

        if (userBean.getChild_a_num() >= 380 && userBean.getChild_b_num() >= 380) {
            service_ratio = ConfigUtil.getConfigValueFloat(ConfigBean.SERVICE_MONEY, configDao).floatValue();
            individual_income_ratio = ConfigUtil.getConfigValueFloat(ConfigBean.INCOME_BIG, configDao).floatValue();
            log.info(service_ratio + "服务费");
            log.info(individual_income_ratio + "税");
        }


        int service_charge = (int) ((float) withdrawBean.getWithdraw_amount() * service_ratio);
        int individual_income_tax = (int) ((float) withdrawBean.getWithdraw_amount() * individual_income_ratio);

        log.info(service_charge + "服务费");
        log.info(individual_income_tax + "税");

        boolean result2 = PayrollUtil.addPayrollRecord(PayrollBean.WITHDRAW, -withdrawBean.getWithdraw_amount(), userBean
                .getUser_id().intValue(), null, "", 0, withdrawBean
                .getWithdraw_remark(), this.userDao, this.payrollDao);


        int withdrawAmount = (int) (withdrawBean.getWithdraw_amount() - service_charge - individual_income_tax);


        boolean result3 = WithdrawUtil.addWithdrawRecord(withdrawAmount, userBean.getUser_id().intValue(), withdrawBean
                .getWithdraw_remark(), withdrawBean.getBank_card(), withdrawBean.getAlipay(), withdrawBean
                .getWechat(), this.userDao, this.withdrawDao);

        if (result2 && result3) {
            baseBean.setMsg("提现信息上传成功");
            baseBean.setCode(SUCCESS);
        } else {
            baseBean.setMsg("提现信息上传失败");
        }
        return baseBean;
    }


    public BaseBean<List<WithdrawBean>> findWithdrawDataByUid(Integer user_id) {
        BaseBean<List<WithdrawBean>> baseBean = new BaseBean();
        Integer exists = this.userDao.getUserExists(user_id);
        if (exists != 0) {
            List<WithdrawBean> withdrawBeanList = this.withdrawDao.findWithdrawDataByUid(user_id);
            if (withdrawBeanList != null && withdrawBeanList.size() != 0) {
                baseBean.setCode(SUCCESS);
                baseBean.setMsg("信息获取成功");
                baseBean.setData(withdrawBeanList);
            } else {
                baseBean.setCode(SUCCESS);
                baseBean.setMsg("信息获取成功");
            }
        } else {
            baseBean.setMsg("用户不存在");

        }
        return baseBean;
    }


    public BaseBean<Integer> findUserWithdrawAmount(Integer user_id) {
        BaseBean<Integer> baseBean = new BaseBean();
        Integer exists = this.userDao.getUserExists(user_id);
        if (exists != 0) {
            Integer amount = this.withdrawDao.findUserWithdrawAmount(user_id);
            if (amount == null) {
                amount = 0;
            }
            baseBean.setCode(SUCCESS);
            baseBean.setMsg("信息获取成功");
            baseBean.setData(amount);
        } else {
            baseBean.setMsg("用户不存在");

        }
        return baseBean;
    }

    @Override
    public BaseBean<List<WithdrawBean>> getWithBeanList(PageBean pageBean) {

        BaseBean<List<WithdrawBean>> baseBean = new BaseBean();

        boolean result = PageUtil.setPageBean(pageBean, userDao);
        if (!result) {
            baseBean.setMsg("没有数据");
            return baseBean;
        }

        List<WithdrawBean> data = withdrawDao.getWithBeanList(pageBean);

        baseBean.setCode(BaseBean.SUCCESS);
        baseBean.setMsg("加载成功");
        baseBean.setData(data);

        return baseBean;

    }

    @Override
    public BaseBean handleWithdraw(WithdrawBean withdrawBean) {

        BaseBean baseBean = new BaseBean();
        
        withdrawDao.handleWithdraw(withdrawBean);

        baseBean.setCode(BaseBean.SUCCESS);
        baseBean.setMsg("设置成功");

        return baseBean;
    }
}
