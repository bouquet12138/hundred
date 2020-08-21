package dy.network.hundred.utils;

import dy.network.hundred.dao.UserDao;
import dy.network.hundred.java_bean.UserBean;

public class BuyIntegerUtil
{

  /**
   * 添加购买积分记录
   * @param userDao
   * @param user_id
   * @param amount
   * @return
   */
  public static int addBuyIntegerRecord(UserDao userDao, Integer user_id, int amount) {
    UserBean userBean = userDao.findUserDataByUserId(user_id);
    if (userBean == null)
      return 0; 
    userBean.setBuy_integral(userBean.getBuy_integral() + amount);
    userDao.updateUserBuyIntegral(userBean);
    return 1;
  }

}
