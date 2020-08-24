package dy.network.hundred.java_bean;

import dy.network.hundred.java_bean.chart_bean.LineChartBean;
import dy.network.hundred.java_bean.chart_bean.PieChartBean;
import lombok.Data;

import java.util.List;

@Data
public class HomeBean {

    private String dailyOrderTotal = "0";
    private String dailyOrderTotalLimit = "0";
    private String dailyCompanyTotal = "0";
    private String dailyActiveUserTotal = "0";
    private String orderTotal;

    private List<LineChartBean> echartsData;
    private List<PieChartBean> echartsData2;
}
