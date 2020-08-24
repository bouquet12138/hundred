package dy.network.hundred.service.impl;

import dy.network.hundred.dao.IntegralDao;
import dy.network.hundred.dao.UserDao;
import dy.network.hundred.java_bean.BaseBean;
import dy.network.hundred.java_bean.HomeBean;
import dy.network.hundred.java_bean.chart_bean.LineChartBean;
import dy.network.hundred.java_bean.chart_bean.PieChartBean;
import dy.network.hundred.java_bean.db_bean.IntegralBean;
import dy.network.hundred.java_bean.db_bean.UserBean;
import dy.network.hundred.service.HomeService;
import dy.network.hundred.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("homeService")
public class HomeServiceImpl implements HomeService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private IntegralDao integralDao;

    //TODO:还没写完
    @Override
    public BaseBean<HomeBean> homeData() {

        BaseBean<HomeBean> baseBean = new BaseBean<>();

        HomeBean homeBean = new HomeBean();

        //饼状图数据
        int normalUserCount = userDao.findRoleNum(UserBean.NORMAL_USER);
        int registerUserCount = userDao.findRoleNum(UserBean.REGISTER_CENTER);

        List<PieChartBean> pieChartBeans = new ArrayList<>();
        pieChartBeans.add(new PieChartBean(normalUserCount, "普通用户"));
        pieChartBeans.add(new PieChartBean(registerUserCount, "报单中心"));
        homeBean.setEchartsData2(pieChartBeans);

        //30天内登录用户
        int thirtyDayUser = userDao.thirtyDayUser(DateUtil.getDay());
        homeBean.setDailyActiveUserTotal(thirtyDayUser + "");

        //理疗套餐
        List<LineChartBean> lineChartBeans = new ArrayList<>();
        List<Integer> data = new ArrayList<>();
        for (int i =6; i >= 0; i--) {
            int count = integralDao.findIntegralBeanCount(i, DateUtil.getDay(), IntegralBean.BUY_SET_MEAL);
            data.add(count);
        }
        lineChartBeans.add(new LineChartBean(0, "理疗套餐", "line", data));
        homeBean.setEchartsData(lineChartBeans);//设置数据

        baseBean.setCode(BaseBean.SUCCESS);
        baseBean.setMsg("获取成功");
        baseBean.setData(homeBean);

        return baseBean;
    }
}
