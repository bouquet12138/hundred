package dy.network.hundred.service.impl;

import dy.network.hundred.dao.DeMachineNumDao;
import dy.network.hundred.dao.IntegralDao;
import dy.network.hundred.dao.UserDao;
import dy.network.hundred.java_bean.*;
import dy.network.hundred.java_bean.db_bean.DeMachineNumBean;
import dy.network.hundred.java_bean.db_bean.IntegralBean;
import dy.network.hundred.java_bean.db_bean.UserBean;
import dy.network.hundred.service.UserService;
import dy.network.hundred.utils.DateUtil;
import dy.network.hundred.utils.IntegerUtil;
import dy.network.hundred.utils.MD5Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service("userService")
public class UserServiceImpl
        implements UserService {

    @Autowired
    private DeMachineNumDao deMachineNumDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private IntegralDao integralDao;

    public BaseBean<UserBean> userLogin(UserBean userBean) {
        BaseBean<UserBean> baseBean = new BaseBean();

        String md5 = MD5Utils.generateMD5(userBean.getLogin_password());
        userBean.setLogin_password(md5);
        Integer userLogin = userDao.userLogin(userBean);

        if (userLogin <= 0) {
            baseBean.setMsg("账号或密码错误");
            return baseBean;
        }
        UserBean user = userDao.findUserPartByPhone_num(userBean.getPhone_num());
        if (UserBean.UN_ENABLE.equals(user.getEnable())) {
            baseBean.setMsg("该账号被冻结");
            return baseBean;
        }

        baseBean.setCode(BaseBean.SUCCESS);
        baseBean.setMsg("登录成功");

        user.setLogin_time(DateUtil.getDate());
        userDao.updateLoginTime(user);
        baseBean.setData(user);

        return baseBean;
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public BaseBean<Integer> userRegister(UserBean userBean) {

        BaseBean<Integer> baseBean = new BaseBean();

        Integer phoneExists = userDao.userPhoneExists(userBean.getPhone_num());

        if (phoneExists > 0) {
            baseBean.setMsg("手机号已经存在");
            return baseBean;
        }

        UserBean recommendBean = userDao.findUserPartByPhone_num(userBean.getRecommend_user_phone());
        recommendBean.setPay_password(MD5Utils.generateMD5(userBean.getPay_password()));
        Integer isTrue = userDao.payPasswordIsTrue(recommendBean);

        if (isTrue <= 0) {
            baseBean.setMsg("支付密码错误");
            return baseBean;
        }

        if (recommendBean == null) {
            baseBean.setMsg("推荐人不存在");
            return baseBean;
        }

        if (!UserBean.REGISTER_CENTER.equals(recommendBean.getGrade())) {//不是报单中心
            baseBean.setMsg("只有报单中心可以注册用户");
            return baseBean;
        }

        if (recommendBean.getIntegral_num() < 898L) {
            baseBean.setMsg("积分不足");
            return baseBean;
        }

        UserBean vertexBean = userDao.findUserPartByPhone_num(userBean.getVertex_user_phone());
        if (vertexBean == null) {
            baseBean.setMsg("安置者不存在");
            return baseBean;
        }

        userBean.setDefaultData();
        userBean.setLogin_password(MD5Utils.generateMD5(userBean.getLogin_password()));
        userBean.setPay_password(MD5Utils.generateMD5("123456"));


        Integer userRegister = userDao.userRegister(userBean);
        if (userRegister <= 0) {
            baseBean.setMsg("注册失败");
            return baseBean;
        }


        UserBean _vertexBean = register(vertexBean.getUser_id(), userBean);

        parentChildNumAdd(_vertexBean.getUser_id());

        if (_vertexBean != null) {

            boolean addRecord = IntegerUtil.addIntegerRecord(IntegralBean.BUY_SET_MEAL, -898L, recommendBean
                    .getUser_id(), null, "注册用户", userDao, integralDao);
            if (addRecord) {
                DeMachineNumBean deMachineNumBean = new DeMachineNumBean(100, userBean.getUser_id(), DateUtil.getDate());
                deMachineNumDao.addDeMachineNumData(deMachineNumBean);//添加一下
                baseBean.setCode(BaseBean.SUCCESS);
                baseBean.setMsg("注册成功");
                baseBean.setData(userBean.getUser_id());
            } else {
                baseBean.setMsg("注册失败");
                userDao.deleteUserByUid(userBean.getUser_id());
            }
        } else {
            baseBean.setMsg("注册失败");
            userDao.deleteUserByUid(userBean.getUser_id());
        }


        return baseBean;
    }


    private UserBean register(int vertex_id, UserBean userBean) {

        UserBean vertexUser = userDao.findUserDataByUserId(vertex_id);
        if (vertexUser == null)
            return null;

        if (vertexUser.getChild_a() == null) {
            vertexUser.child_a_num_add();
            userDao.updateUserChildANum(vertexUser);
            vertexUser.setChild_a(userBean.getUser_id());
            userDao.updateUserDataRecommendAfter(vertexUser);
        } else if (vertexUser.getChild_b() == null) {
            vertexUser.child_b_num_add();
            userDao.updateUserChildBNum(vertexUser);
            vertexUser.setChild_b(userBean.getUser_id());
            userDao.updateUserDataRecommendAfter(vertexUser);
        } else {
            Random random = new Random();
            int index = random.nextInt(2);
            switch (index) {
                case 0:
                    return register(vertexUser.getChild_a(), userBean);
                case 1:
                    return register(vertexUser.getChild_b(), userBean);
            }
        }
        return vertexUser;
    }


    private void parentChildNumAdd(int user_id) {
        UserBean parent = null;

        parent = userDao.findUserDataByChildA(user_id);
        if (parent != null) {
            parent.child_a_num_add();
            userDao.updateUserChildANum(parent);
            parentChildNumAdd(parent.getUser_id());
            return;
        }
        parent = userDao.findUserDataByChildB(user_id);
        if (parent != null) {
            parent.child_b_num_add();
            userDao.updateUserChildBNum(parent);
            parentChildNumAdd(parent.getUser_id());
            return;
        }
    }


    public BaseBean updateUserDataByUserId(UserBean userBean) {
        BaseBean baseBean = new BaseBean();
        if (userBean.getHead_img_id() != null && userBean.getHead_img_id() == 0) {
            userBean.setHead_img_id(null);
        }
        log.info(userBean.toString());
        Integer dataUser = userDao.updateUserDataByUserId(userBean);
        log.info(dataUser + "修改数据");
        if (dataUser > 0) {
            baseBean.setCode(BaseBean.SUCCESS);
            baseBean.setMsg("修改成功");
        } else {

            baseBean.setMsg("修改失败");
        }
        return baseBean;
    }


    public BaseBean updateUserLoginPassword2(UserBean userBean) {
        BaseBean baseBean = new BaseBean();
        String login_pass = MD5Utils.generateMD5(userBean.getNew_login_password());
        userBean.setNew_login_password(login_pass);
        Integer loginPassword2 = userDao.updateUserLoginPassword(userBean);
        if (loginPassword2 > 0) {
            baseBean.setCode(BaseBean.SUCCESS);
            baseBean.setMsg("密码修改成功");
        } else {

            baseBean.setMsg("密码修改失败");
        }
        return baseBean;
    }


    public BaseBean updateUserPayPassword1(UserBean userBean) {
        BaseBean baseBean = new BaseBean();

        String pay_pass = MD5Utils.generateMD5(userBean.getOld_pay_password());
        userBean.setPay_password(pay_pass);
        Integer isTrue = userDao.payPasswordIsTrue(userBean);
        if (isTrue != 0) {

            String new_pay_pass = MD5Utils.generateMD5(userBean.getNew_pay_password());
            System.out.println(new_pay_pass);
            userBean.setNew_pay_password(new_pay_pass);
            Integer payPassword1 = userDao.updateUserPayPassword(userBean);
            if (payPassword1 > 0) {
                baseBean.setCode(BaseBean.SUCCESS);
                baseBean.setMsg("密码修改成功");
            } else {

                baseBean.setMsg("密码修改失败");
            }
        } else {

            baseBean.setMsg("旧密码不正确");
        }
        return baseBean;
    }


    public BaseBean updateUserPayPassword2(UserBean userBean) {
        BaseBean baseBean = new BaseBean();

        String new_pay_pass = MD5Utils.generateMD5(userBean.getNew_pay_password());
        userBean.setNew_pay_password(new_pay_pass);
        Integer payPassword2 = userDao.updateUserPayPassword(userBean);
        if (payPassword2 > 0) {
            baseBean.setCode(BaseBean.SUCCESS);
            baseBean.setMsg("密码修改成功");
        } else {

            baseBean.setMsg("密码修改失败");
        }
        return baseBean;
    }


    public Boolean getUserZoneNumber(String phone_num) {
        Boolean flag = Boolean.valueOf(false);
        String number = userDao.getUserZoneNumberByPhoneNum(phone_num);
        if ("1".equals(number)) {
            flag = Boolean.valueOf(true);
        }
        return flag;
    }


    public BaseBean<UserBean> findUserDataByUserId(Integer userId) {
        BaseBean<UserBean> baseBean = new BaseBean();
        UserBean data = userDao.findUserDataByUserId(userId);
        if (data != null) {
            baseBean.setData(data);
            baseBean.setCode(BaseBean.SUCCESS);
            baseBean.setMsg("获取数据成功");
        } else {

            baseBean.setMsg("获取数据失败");
        }
        return baseBean;
    }


    public BaseBean<UserBean> findUserDataByUserPhoneNum(String phone_num) {
        BaseBean<UserBean> baseBean = new BaseBean();
        UserBean data = userDao.findUserDataByUserPhoneNum(phone_num);
        if (data != null) {
            baseBean.setData(data);
            baseBean.setCode(BaseBean.SUCCESS);
            baseBean.setMsg("获取数据成功");
        } else {

            baseBean.setMsg("手机号不存在");
        }
        return baseBean;
    }


    public BaseBean<List<UserBean>> findUserDataByAddress(UserBean userBean) {
        BaseBean<List<UserBean>> baseBean = new BaseBean();
        System.out.println(userBean.getProvince() + "---" + userBean.getCity() + "---" + userBean.getDistrict());
        List<UserBean> userBeanList = userDao.findUserDataByAddress(userBean);
        if (userBeanList != null) {
            baseBean.setData(userBeanList);
            baseBean.setCode(BaseBean.SUCCESS);
            baseBean.setMsg("获取数据成功");
        } else {

            baseBean.setMsg("获取数据失败");
        }
        return baseBean;
    }


    public BaseBean<List<UserBean>> findUserGroup(Integer user_id) {
        BaseBean<List<UserBean>> baseBean = new BaseBean();
        // List<Integer> groupSon = findUserGroupSon(user_id);
        List<UserBean> list = new ArrayList<>();
        UserBean userBean = userDao.findUserDataByUserId(user_id);

        if (userBean == null) {
            baseBean.setMsg("该用户不存在");
            return baseBean;
        }

        if (userBean.getChild_a() != null) {
            UserBean child_a = userDao.findUserDataByUserId(userBean.getChild_a());
            list.add(child_a);
        }
        if (userBean.getChild_b() != null) {
            UserBean child_b = userDao.findUserDataByUserId(userBean.getChild_b());
            list.add(child_b);
        }
        baseBean.setCode(BaseBean.SUCCESS);
        baseBean.setData(list);//设置数据
        return baseBean;
    }


    public BaseBean<List<UserBean>> getRecommendUserByUid(Integer userId) {
        BaseBean<List<UserBean>> baseBean = new BaseBean();
        Integer exists = userDao.getUserExists(userId);
        if (exists != 0) {


            String phoneNum = userDao.getUserPhoneNumByUid(userId);
            List<UserBean> userBeanList = userDao.findUserDataByUserRecommend_user_phone(phoneNum);
            if (userBeanList != null && userBeanList.size() != 0) {
                baseBean.setCode(BaseBean.SUCCESS);
                baseBean.setData(userBeanList);
                baseBean.setMsg("信息获取成功");
            } else {

                baseBean.setMsg("信息获取失败");
            }
        }
        return baseBean;
    }


    public BaseBean openZoneThree(Integer user_id) {
        BaseBean baseBean = new BaseBean();

        UserBean userBean = userDao.findUserDataByUserId(user_id);
        if (userBean == null) {
            baseBean.setMsg("用户不存在");
            return baseBean;
        }


        int child_a = userBean.getChild_a_num();
        int child_b = userBean.getChild_b_num();

        if (child_a < 380 || child_b < 380) {
            baseBean.setMsg("您不符合开通三区的条件");
            return baseBean;
        }

        Integer zoneThree = userDao.openZoneThree(user_id);
        if (zoneThree > 0) {
            baseBean.setCode(BaseBean.SUCCESS);
            baseBean.setMsg("开通成功");
        } else {
            baseBean.setMsg("开通失败");
        }


        return baseBean;
    }

    @Override
    public BaseBean<List<UserBean>> getUserList(PageBean pageBean) {
        BaseBean<List<UserBean>> baseBean = new BaseBean<>();

        List<UserBean> userBeans = userDao.getPageUserList(pageBean);

        baseBean.setCode(BaseBean.SUCCESS);//成功
        baseBean.setMsg("加载成功");
        baseBean.setData(userBeans);//设置用户信息

        return baseBean;
    }

    @Override
    public BaseBean enableUser(UserBean userBean) {
        BaseBean baseBean = new BaseBean<>();

        int result = userDao.enableUser(userBean);

        if (result > 0) {
            baseBean.setCode(BaseBean.SUCCESS);//成功
            baseBean.setMsg("设置成功");
        } else {
            baseBean.setMsg("设置失败");
        }

        return baseBean;
    }


    @Override
    public BaseBean gradeUser(UserBean userBean) {
        BaseBean baseBean = new BaseBean<>();

        int result = userDao.gradeUser(userBean);

        if (result > 0) {
            baseBean.setCode(BaseBean.SUCCESS);//成功
            baseBean.setMsg("设置成功");
        } else {
            baseBean.setMsg("设置失败");
        }

        return baseBean;
    }


    public BaseBean updateUserLoginPassword1(UserBean userBean) {
        BaseBean baseBean = new BaseBean();

        String old_login_pass = MD5Utils.generateMD5(userBean.getOld_login_password());
        userBean.setLogin_password(old_login_pass);
        Integer userLogin = userDao.loginPasswordIsTrue(userBean);
        if (userLogin > 0) {

            String new_login_pass = MD5Utils.generateMD5(userBean.getNew_login_password());
            userBean.setNew_login_password(new_login_pass);
            Integer loginPassword1 = userDao.updateUserLoginPassword(userBean);
            if (loginPassword1 > 0) {
                baseBean.setCode(BaseBean.SUCCESS);
                baseBean.setMsg("密码修改成功");
            } else {

                baseBean.setMsg("密码修改失败");
            }
        } else {

            baseBean.setMsg("旧密码错误");
        }
        return baseBean;
    }

}
