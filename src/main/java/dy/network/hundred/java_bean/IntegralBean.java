package dy.network.hundred.java_bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import dy.network.hundred.utils.DateUtil;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class IntegralBean {

    public static final String BUY_SET_MEAL = "购买套餐";
    public static final String TRANSFERS_BETWEEN = "互转";
    public static final String RECHARGE = "充值";

    public static final String OTHER = "其他";

    private int integral_id = 0;


    private long transaction_amount = 0L;
    private String transaction_time;
    private Integer user_id;
    private Integer target_user_id;
    private UserBean targetUserBean;


    private String integral_type;
    private String transaction_remark;
    private long remain_record;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String pay_password;


    public IntegralBean(long amount, Integer user_id, Integer target_user_id, String integral_type, String remark, long remain_record) {
        this.transaction_amount = amount;
        this.user_id = user_id;
        this.target_user_id = target_user_id;
        this.integral_type = integral_type;
        this.transaction_remark = remark;
        this.remain_record = remain_record;
        this.transaction_time = DateUtil.getDate();
    }
}

