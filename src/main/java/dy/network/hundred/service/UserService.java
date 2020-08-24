package dy.network.hundred.service;


import dy.network.hundred.java_bean.PageBean;
import dy.network.hundred.java_bean.db_bean.UserBean;
import dy.network.hundred.java_bean.BaseBean;

import java.util.List;
import javax.websocket.server.PathParam;

public interface UserService {

    BaseBean<UserBean> userLogin(UserBean paramUserBean);

    BaseBean<Integer> userRegister(UserBean paramUserBean);

    BaseBean updateUserDataByUserId(UserBean paramUserBean);

    BaseBean updateUserLoginPassword1(UserBean paramUserBean);

    BaseBean updateUserLoginPassword2(UserBean paramUserBean);

    BaseBean updateUserPayPassword1(UserBean paramUserBean);

    BaseBean updateUserPayPassword2(UserBean paramUserBean);

    Boolean getUserZoneNumber(@PathParam("phone_num") String paramString);

    BaseBean<UserBean> findUserDataByUserId(@PathParam("user_id") Integer paramInteger);

    BaseBean<UserBean> findUserDataByUserPhoneNum(@PathParam("phone_num") String paramString);

    BaseBean<List<UserBean>> findUserDataByAddress(UserBean paramUserBean);

    BaseBean<List<UserBean>> findUserGroup(@PathParam("user_id") Integer paramInteger);

    BaseBean<List<UserBean>> getRecommendUserByUid(@PathParam("user_id") Integer paramInteger);

    BaseBean openZoneThree(@PathParam("user_id") Integer paramInteger);

    BaseBean<List<UserBean>> getUserList(PageBean pageBean);

    BaseBean enableUser(UserBean userBean);

    BaseBean gradeUser(UserBean userBean);
}
