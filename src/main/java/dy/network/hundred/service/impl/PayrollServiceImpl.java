package dy.network.hundred.service.impl;

import dy.network.hundred.dao.ALGDao;
import dy.network.hundred.dao.IntegralDao;
import dy.network.hundred.dao.PayrollDao;
import dy.network.hundred.dao.UserDao;
import dy.network.hundred.java_bean.BaseBean;
import dy.network.hundred.java_bean.PayrollBean;
import dy.network.hundred.java_bean.UserBean;
import dy.network.hundred.service.PayrollService;
import dy.network.hundred.task.SendPayroll;
import dy.network.hundred.utils.*;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service("payrollService")
public class PayrollServiceImpl
        implements PayrollService {

    @Autowired
    private IntegralDao integralDao;

    @Autowired
    private PayrollDao payrollDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ALGDao algDao;

    public BaseBean<List<PayrollBean>> findUserSalaryInformation(Integer user_id) {
        BaseBean<List<PayrollBean>> baseBean = new BaseBean();
        Integer exists = userDao.getUserExists(user_id);
        if (exists != 0) {
            List<PayrollBean> payrollBeans = payrollDao.findUserSalaryInformation(user_id);
            if (payrollBeans != null && payrollBeans.size() != 0) {
                baseBean.setCode(BaseBean.SUCCESS);
                baseBean.setMsg("信息加载成功");
                baseBean.setData(payrollBeans);
            } else {
                baseBean.setCode(BaseBean.SUCCESS);
                baseBean.setMsg("信息加载成功");
            }
        } else {

            baseBean.setMsg("该用户不存在");
        }
        return baseBean;
    }


    public BaseBean<Long> findUserPayrollNum(Integer user_id) {
        BaseBean<Long> baseBean = new BaseBean();
        Integer exists = userDao.getUserExists(user_id);
        if (exists != 0) {
            UserBean property = userDao.getUserProperty(user_id);
            baseBean.setMsg("信息获取成功");
            baseBean.setCode(BaseBean.SUCCESS);
            baseBean.setData(Long.valueOf(property.getPayroll_num()));
        } else {

            baseBean.setMsg("用户不存在");
        }
        return baseBean;
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public BaseBean transferAccounts(PayrollBean payrollBean) {
        BaseBean baseBean = new BaseBean();
        UserBean user = userDao.findUserDataByUserId(payrollBean.getUser_id());
        if (user == null) {
            baseBean.setMsg("用户不存在");
            return baseBean;
        }

        UserBean targetUser = userDao.findUserDataByUserId(payrollBean.getTarget_user_id());
        if (targetUser == null) {
            baseBean.setMsg("目标用户不存在");
            return baseBean;
        }

        if (payrollBean.getUser_id() == payrollBean.getTarget_user_id()) {
            baseBean.setMsg("不能给自己转账");
            return baseBean;
        }


        user.setPay_password(MD5Utils.generateMD5(payrollBean.getPay_password()));
        Integer isTrue = userDao.payPasswordIsTrue(user);

        if (isTrue <= 0) {
            baseBean.setMsg("支付密码错误");
            return baseBean;
        }

        long amount = payrollBean.getPayroll_amount();

        if (user.getPayroll_num() < amount) {
            baseBean.setMsg("余额不足");
            return baseBean;
        }


        boolean result = PayrollUtil.addPayrollRecord(payrollBean.getType(), -amount, payrollBean.getUser_id(), payrollBean.getTarget_user_id(), null, 0, payrollBean
                .getTransaction_remark(), userDao, payrollDao);

        boolean result1 = PayrollUtil.addPayrollRecord(payrollBean.getType(), amount, payrollBean.getTarget_user_id(), payrollBean.getUser_id(), null, 0, payrollBean
                .getTransaction_remark(), userDao, payrollDao);

        if (result && result1) {
            baseBean.setCode(BaseBean.SUCCESS);
            baseBean.setMsg("转账成功");
        } else {

            baseBean.setMsg("转账失败");
        }

        return baseBean;
    }


    public BaseBean payrollToIntegral(PayrollBean payrollBean) {
        BaseBean baseBean = new BaseBean();
        UserBean user = userDao.findUserDataByUserId(payrollBean.getUser_id());
        if (user == null) {
            baseBean.setMsg("用户不存在");
            return baseBean;
        }
        user.setPay_password(MD5Utils.generateMD5(payrollBean.getPay_password()));
        Integer isTrue = userDao.payPasswordIsTrue(user);

        if (isTrue <= 0) {
            baseBean.setMsg("支付密码错误");
            return baseBean;
        }

        int amount = (int) payrollBean.getPayroll_amount();

        if (user.getPayroll_num() < amount) {
            baseBean.setMsg("工资不足");
            return baseBean;
        }

        PayrollUtil.addPayrollRecord(PayrollBean.CONVERSION, -amount, user.getUser_id(), null, "", 0, payrollBean
                .getTransaction_remark(), userDao, payrollDao);

        IntegerUtil.addIntegerRecord(PayrollBean.CONVERSION, amount, user.getUser_id(), null, payrollBean
                .getTransaction_remark(), userDao, integralDao);


        baseBean.setCode(BaseBean.SUCCESS);
        baseBean.setMsg("转换成功");

        return baseBean;
    }

    @Transactional
    public BaseBean sendPayroll() {

        BaseBean baseBean = new BaseBean();

        PayrollBean payrollBean = new PayrollBean();
        payrollBean.setType(PayrollBean.PROMOTE_INCOME);
        payrollBean.setTransaction_time(DateUtil.getDay());
        log.info(DateUtil.getDay());

        List<PayrollBean> payrollBeans = payrollDao.isSendPayroll(payrollBean);

        if (!CollectionUtils.isEmpty(payrollBeans)) {
            baseBean.setMsg("今日已发工资");
            return baseBean;
        }


        SendPayroll sendPayroll = new SendPayroll(userDao, payrollDao, algDao);
        sendPayroll.completeDelivery();

        baseBean.setCode(BaseBean.SUCCESS);
        baseBean.setMsg("工资发布成功");

        return baseBean;
    }
}
