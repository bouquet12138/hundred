package dy.network.hundred.java_bean;

import lombok.Data;

@Data
public class ConfigBean {

    public static final String STATUS_UN_ENABLED = "0";
    public static final String STATUS_ENABLED = "1";
    public static final String SMS_OPEN = "jingye.sms_open";
    public static final String SERVICE_MONEY = "jingye.payroll.service";
    public static final String SERVICE_BIG = "jingye.payroll.service_big";
    public static final String INCOME = "jingye.payroll.income";
    public static final String INCOME_BIG = "jingye.payroll.income_big";
    public static final String REGISTER_INCOME = "jingye.payroll.register_income";

    private int config_id;
    private String config_name;
    private String config_value;
    private String config_status;
    private String config_describe;
    private String insert_time;
    private String update_time;





}
