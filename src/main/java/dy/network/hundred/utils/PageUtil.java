package dy.network.hundred.utils;

import dy.network.hundred.dao.UserDao;
import dy.network.hundred.java_bean.BaseBean;
import dy.network.hundred.java_bean.PageBean;
import dy.network.hundred.java_bean.db_bean.UserBean;

import java.util.ArrayList;
import java.util.List;

public class PageUtil {

    public static boolean setPageBean(PageBean pageBean, UserDao userDao){
        List<UserBean> userBeans = new ArrayList<>();
        if (!TextUtil.isEmpty(pageBean.getPhone_num()))
            userBeans = userDao.findUserPhoneDim(pageBean.getPhone_num());

        if (!TextUtil.isEmpty(pageBean.getName()))
            userBeans = userDao.findUserListByNameDim(pageBean.getName());

        if (!TextUtil.isEmpty(pageBean.getPhone_num()) || !TextUtil.isEmpty(pageBean.getName())) {
            if (CollectionUtils.isEmpty(userBeans)) {
               return false;
            }
        }

        List<Integer> userIds = new ArrayList<>();
        for (UserBean userBean : userBeans)
            userIds.add(userBean.getUser_id());

        pageBean.setUserIds(userIds);
        return true;
    }

}
