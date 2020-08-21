package dy.network.hundred.service.impl;

import dy.network.hundred.dao.UserDao;
import dy.network.hundred.java_bean.BaseBean;
import dy.network.hundred.java_bean.HomeBean;
import dy.network.hundred.java_bean.UserBean;
import dy.network.hundred.service.HomeService;
import dy.network.hundred.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("homeService")
public class HomeServiceImpl implements HomeService {

    @Autowired
    private UserDao userDao;

    //TODO:还没写完
    @Override
    public BaseBean<HomeBean> homeData() {

        BaseBean<HomeBean> baseBean = new BaseBean<>();

        HomeBean homeBean = new HomeBean();

        int normalUserCount = userDao.findRoleNum(UserBean.NORMAL_USER);
        int registerUserCount = userDao.findRoleNum(UserBean.REGISTER_CENTER);

        homeBean.setEchartsData2(registerUserCount + "/" + (normalUserCount + registerUserCount));

        int thirtyDayUser = userDao.thirtyDayUser(DateUtil.getDay());

        homeBean.setDailyActiveUserTotal(thirtyDayUser + "");

        baseBean.setCode(BaseBean.SUCCESS);
        baseBean.setData(homeBean);

        return baseBean;
    }
}
