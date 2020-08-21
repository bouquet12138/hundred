package dy.network.hundred.java_bean;

import lombok.Data;

@Data
public class HomeBean {

    private String dailyOrderTotal = "0";
    private String dailyOrderTotalLimit = "0";
    private String dailyCompanyTotal = "0";
    private String dailyActiveUserTotal = "0";
    private String orderTotal;

    private String echartsData2;
}
