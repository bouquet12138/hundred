package dy.network.hundred.utils;


import dy.network.hundred.dao.IntegralDao;
import dy.network.hundred.dao.UserDao;
import dy.network.hundred.java_bean.IntegralBean;
import dy.network.hundred.java_bean.UserBean;

public class IntegerUtil {

    public static boolean addIntegerRecord(String integral_type, long amount, int user_id, Integer target_user_id, String remark, UserDao userDao, IntegralDao integralDao) {
        UserBean userBean = userDao.findUserDataByUserId(user_id);
        if (userBean == null) {
            return false;
        }

        IntegralBean integralBean = new IntegralBean(amount, user_id, target_user_id, integral_type, remark, userBean.getIntegral_num() + amount);

        Integer data = integralDao.addIntegralData(integralBean);
        userBean.setIntegral_num(integralBean.getRemain_record());
        Integer up1 = userDao.updateUserIntegral_num(userBean);
        return (data > 0 && up1 > 0);
    }
}
