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
import dy.network.hundred.utils.CollectionUtils;
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

        int dayNum = 0;
        int dayMoney = 0;
        int weekNum = 0;

        //理疗套餐
        List<LineChartBean> lineChartBeans = new ArrayList<>();
        List<Integer> data = new ArrayList<>();
        for (int i = 6; i >= 0; i--) {
            List<IntegralBean> integralBeans = integralDao.findIntegralBeanCount(i, DateUtil.getDay(), IntegralBean.BUY_SET_MEAL);
            int count = 0;
            data.add(count);
            if (!CollectionUtils.isEmpty(integralBeans)) {
                if (i == 0) {
                    dayNum = integralBeans.size();
                    for (IntegralBean integralBean : integralBeans)
                        dayMoney += Math.abs(integralBean.getTransaction_amount());
                }
                weekNum += integralBeans.size();
            }
        }

        //总收入
        List<IntegralBean> totalIntegralBean = integralDao.findTotalIntegralBean(IntegralBean.BUY_SET_MEAL);//购买套餐
        int totalMoney = 0;
        if (!CollectionUtils.isEmpty(totalIntegralBean))
            for (IntegralBean integralBean : totalIntegralBean)
                totalMoney += Math.abs(integralBean.getTransaction_amount());

        lineChartBeans.add(new LineChartBean(0, "理疗套餐", "line", data));

        homeBean.setEchartsData(lineChartBeans);//设置数据

        homeBean.setDailyOrderTotal(dayNum + "");//日销数量
        homeBean.setDailyCompanyTotal(dayMoney + "");//日销钱
        homeBean.setDailyOrderTotalLimit(weekNum + "");//七天销售
        homeBean.setOrderTotal(totalMoney + "");//总收入


        baseBean.setCode(BaseBean.SUCCESS);
        baseBean.setMsg("获取成功");
        baseBean.setData(homeBean);

        return baseBean;
    }
}
