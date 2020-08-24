package dy.network.hundred.task;


import dy.network.hundred.dao.ALGDao;
import dy.network.hundred.dao.PayrollDao;
import dy.network.hundred.dao.UserDao;
import dy.network.hundred.java_bean.ALGBean;
import dy.network.hundred.java_bean.db_bean.PayrollBean;
import dy.network.hundred.java_bean.db_bean.UserBean;
import dy.network.hundred.utils.CollectionUtils;
import dy.network.hundred.utils.DateUtil;
import dy.network.hundred.utils.PayrollUtil;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


public class SendPayroll {

    private UserDao userDao;
    private PayrollDao payrollDao;
    private ALGDao algDao;

    private List<UserBean> mUserBeans;
    private List<ALGBean> mAlgBeans;

    public SendPayroll(UserDao userDao, PayrollDao payrollDao, ALGDao algDao) {
        this.userDao = userDao;
        this.payrollDao = payrollDao;
        this.algDao = algDao;
        mUserBeans = new ArrayList<>();
    }

    @Transactional
    public void completeDelivery() {
        mAlgBeans = algDao.findAlgAll();

        PayrollBean payrollBean = new PayrollBean();
        payrollBean.setType(PayrollBean.PROMOTE_INCOME);
        payrollBean.setTransaction_time(DateUtil.getDay());
        List<PayrollBean> payrollBeans = payrollDao.isSendPayroll(payrollBean);

        if (!CollectionUtils.isEmpty(payrollBeans))
            return;

        mUserBeans = userDao.findUserAll();

        for (UserBean userBean : mUserBeans) {
            zoneCalculate(userBean);
        }

        mUserBeans.clear();
    }


    private void zoneCalculate(UserBean userBean) {
        ALGBean algBean;

        int a = userBean.getChild_a_num();
        int b = userBean.getChild_b_num();


        PayrollBean payrollBean = payrollDao.findUserSalaryInformationByUidAndType(userBean.getUser_id());


        algBean = getAccordALGBean(a, b);

        if (algBean != null) {
            increasePayroll(userBean, payrollBean, algBean
                    .getNum_a() + ":" + algBean.getNum_b(), algBean
                    .getCumulativeDay(a, b, 0), algBean.getAdd_money());
        }
    }

    /**
     * 得到对应的数据
     *
     * @param a
     * @param b
     * @return
     */
    private ALGBean getAccordALGBean(int a, int b) {
        if (!(mAlgBeans.get(0)).WhetherTheCompound(a, b)) {
            return null;
        }
        for (int j = 1; j < mAlgBeans.size(); j++) {
            ALGBean algBean = mAlgBeans.get(j);
            if (!algBean.WhetherTheCompound(a, b)) {
                ALGBean lastAlgBean = mAlgBeans.get(j - 1);
                return lastAlgBean;
            }
            if (j == mAlgBeans.size() - 1) {
                return algBean;
            }
        }
        return null;
    }


    /**
     * 插入记录
     */
    private boolean increasePayroll(UserBean userBean, PayrollBean payrollBean, String promote_income_type, int addDay, int addMoney) {
        if (payrollBean == null) {

            insertPromotePayroll(userBean, addMoney, promote_income_type, 1);
            return true;
        }

        if (!promote_income_type.equals(payrollBean.getPromote_income_type())) {

            insertPromotePayroll(userBean, addMoney, promote_income_type, 1);
            return true;
        }


        int nowCumulativeDay = payrollBean.getCumulative_day();
        if (nowCumulativeDay < addDay) {
            insertPromotePayroll(userBean, addMoney, promote_income_type, nowCumulativeDay + 1);
            return true;
        }

        return false;
    }


    public void insertPromotePayroll(UserBean userBean, int addMoney, String promote_income_type, int cumulative_day) {
        PayrollUtil.addPayrollRecord(PayrollBean.PROMOTE_INCOME, addMoney, userBean.getUser_id(), null, promote_income_type, cumulative_day, "推广收入", userDao, payrollDao);
    }
}
