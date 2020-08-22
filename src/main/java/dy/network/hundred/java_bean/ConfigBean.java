package dy.network.hundred.java_bean;

import lombok.Data;

@Data
public class ConfigBean {

    public static final String STATUS_UN_ENABLED = "0";
    public static final String STATUS_ENABLED = "1";
    public static final String SMS_OPEN = "hunderd.sms_open";
    public static final String SERVICE_MONEY = "hunderd.payroll.service";
    public static final String SERVICE_BIG = "hunderd.payroll.service_big";
    public static final String INCOME = "hunderd.payroll.income";
    public static final String INCOME_BIG = "hunderd.payroll.income_big";
    public static final String REGISTER_INCOME = "hunderd.payroll.register_income";

    public static final String DEMACHINE_RECHARGE = "hunderd.demachine_recharge";
    public static final String DEMACHINE_ACCOUNT = "hunderd.demachine_account";
    public static final String DEMACHINE_PASS = "hunderd.demachine_pass";

    private int config_id;
    private String config_name;
    private String config_value;
    private String config_status;
    private String config_describe;
    private String insert_time;
    private String update_time;


}
