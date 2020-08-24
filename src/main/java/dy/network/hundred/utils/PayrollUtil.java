package dy.network.hundred.utils;

import dy.network.hundred.dao.PayrollDao;
import dy.network.hundred.dao.UserDao;
import dy.network.hundred.java_bean.db_bean.PayrollBean;
import dy.network.hundred.java_bean.db_bean.UserBean;

public class PayrollUtil {

    public static boolean addPayrollRecord(String payroll_type, long amount, int user_id, Integer target_user_id, String promote_income_type, int cumulative_day, String remark, UserDao userDao, PayrollDao payrollDao) {
        UserBean userBean = userDao.findUserDataByUserId(user_id);
        if (userBean == null) {
            return false;
        }
        userBean.setPayroll_num(userBean.getPayroll_num() + amount);
        userDao.updateUserPayroll(userBean);


        PayrollBean addPayrollBean = new PayrollBean(amount, DateUtil.getDate(), user_id, target_user_id, payroll_type, remark, promote_income_type, cumulative_day, userBean.getPayroll_num());

        Integer result = payrollDao.SalaryInformation(addPayrollBean);

        return (result > 0);
    }


}
