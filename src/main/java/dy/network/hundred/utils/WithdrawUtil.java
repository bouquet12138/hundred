package dy.network.hundred.utils;


import dy.network.hundred.dao.UserDao;
import dy.network.hundred.dao.WithdrawDao;
import dy.network.hundred.java_bean.db_bean.UserBean;
import dy.network.hundred.java_bean.db_bean.WithdrawBean;

public class WithdrawUtil
{
  public static boolean addWithdrawRecord(int amount, int user_id, String withdraw_remark, String bank_card, String alipay, String wechat, UserDao userDao, WithdrawDao withdrawDao) {
    UserBean userBean = userDao.findUserDataByUserId(user_id);
    if (userBean == null) {
      return false;
    }

    
    WithdrawBean withdrawBean = new WithdrawBean(amount, user_id, withdraw_remark, bank_card, alipay, wechat, userBean.getWithdraw_num() + amount);
    withdrawDao.userWithdrawal(withdrawBean);
    
    userBean.setWithdraw_num(withdrawBean.getWithdraw_amount() + userBean.getWithdraw_num());
    Integer result1 = userDao.updateUserWithdraw_num(userBean);
    return (result1 > 0);
  }
}
