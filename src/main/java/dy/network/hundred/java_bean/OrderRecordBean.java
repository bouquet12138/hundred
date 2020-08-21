package dy.network.hundred.java_bean;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderRecordBean {
    public static final String STATUS_WAIT_DELIVERY = "待发货";
    public static final String STATUS_HAD_DELIVERY = "已发货";
    public static final String STATUS_COMPLETE = "已完成";
    public static final String BUY_PAYROLL = "工资";
    public static final String BUY_INTEGRAL = "积分";
    private Integer order_id;
    private Integer user_id;
    private String pay_pass;
    private int good_id;
    private GoodBean good_bean;

    private String good_type;
    private String serial;
    private String status;
    private int good_count;
    private String receive_address;
    private String receive_detail_address;
    private String receive_name;
    private String receive_phone;
    private String insert_time;
    private String buy_type;


}

