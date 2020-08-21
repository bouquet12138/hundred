package dy.network.hundred.controller;


import dy.network.hundred.java_bean.PageBean;
import dy.network.hundred.java_bean.UserBean;
import dy.network.hundred.service.UserService;
import dy.network.hundred.java_bean.BaseBean;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;


    @PostMapping({"/login"})
    public BaseBean<UserBean> userLogin(@RequestBody UserBean userBean) {
        log.info("");
        return userService.userLogin(userBean);
    }

    @PostMapping({"/register"})
    public BaseBean<Integer> userRegister(@RequestBody UserBean userBean) {
        log.info("");
        return userService.userRegister(userBean);
    }


    @PostMapping({"/modify_user_info"})
    public BaseBean updateUserDataByUid(@RequestBody UserBean userBean) {
        return userService.updateUserDataByUserId(userBean);
    }


    @PostMapping({"/modify_login_password_with_old"})
    public BaseBean updateUserLoginPassword(@RequestBody UserBean userBean) {

        return userService.updateUserLoginPassword1(userBean);
    }

    @PostMapping({"/modify_login_password"})
    public BaseBean updateUserLoginPassword2(@RequestBody UserBean userBean) {
        return userService.updateUserLoginPassword2(userBean);
    }


    @PostMapping({"/modify_pay_password"})
    public BaseBean updateUserPayPassword2(@RequestBody UserBean userBean) {
        return userService.updateUserPayPassword2(userBean);
    }


    @PostMapping({"/modify_pay_password_with_old"})
    public BaseBean updateUserPayPassword1(@RequestBody UserBean userBean) {
        return userService.updateUserPayPassword1(userBean);
    }


    @PostMapping({"/get_user_info"})
    public BaseBean<UserBean> findUserDataByUserId(@RequestBody UserBean userBean) {
        return userService.findUserDataByUserId(userBean.getUser_id());
    }


    @PostMapping({"/get_user_info_with_phone"})
    public BaseBean<UserBean> findUserDataByUserPhoneNum(@RequestBody UserBean userBean) {
        return userService.findUserDataByUserPhoneNum(userBean.getPhone_num());
    }


    @PostMapping({"/get_user_info_with_place"})
    public BaseBean<List<UserBean>> findUserDataByAddress(@RequestBody UserBean userBean) {
        return userService.findUserDataByAddress(userBean);
    }


    @PostMapping({"/my_team"})
    public BaseBean<List<UserBean>> findUserGroup(@RequestBody UserBean userBean) {
        return userService.findUserGroup(userBean.getUser_id());
    }


    @PostMapping({"/promote_record"})
    public BaseBean<List<UserBean>> getRecommendUserByUid(@RequestBody UserBean userBean) {
        return userService.getRecommendUserByUid(userBean.getUser_id());
    }


    @PostMapping({"/open_three_areas"})
    public BaseBean openZoneThree(@RequestBody UserBean userBean) {
        return userService.openZoneThree(userBean.getUser_id());
    }

    @GetMapping("/getUserList")
    public BaseBean<List<UserBean>> getUserList(Integer page,  Integer limit,  String name, String phone_num) {

        PageBean pageBean = new PageBean(page, limit, name, phone_num);

        return userService.getUserList(pageBean);
    }

    @PostMapping("/enable_user")
    public BaseBean enableUser(@RequestBody UserBean userBean) {
        return userService.enableUser(userBean);
    }

    @PostMapping("/grade_user")
    public BaseBean gradeUser(@RequestBody UserBean userBean) {
        return userService.gradeUser(userBean);
    }

}
