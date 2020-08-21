package dy.network.hundred.java_bean;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PayrollBean {

    public static final String PROMOTE_INCOME = "推广收入";
    public static final String REGISTER_INCOME ="注册收入";
    public static final String WITHDRAW = "提现";
    public static final String TRANSFERS_BETWEEN = "互转";

    public static final String CONVERSION = "转换";

    public static final String OTHER = "其他";

    private int payroll_id;
    private long payroll_amount;
    private String transaction_time;

    private int user_id;
    private String type;
    private String transaction_remark;
    private String promote_income_type;
    private int cumulative_day;
    private long remain_record;
    private Integer target_user_id;
    private UserBean targetUserBean;
    private String pay_password;

    /**
     * amount, GetDate.getDate(), user_id, target_user_id, payroll_type, remark, promote_income_type, cumulative_day, userBean.getPayroll_num()
     *
     * @param amount
     * @param date
     * @param user_id
     * @param target_user_id
     * @param payroll_type
     * @param remark
     * @param promote_income_type
     * @param cumulative_day
     * @param payroll_num
     */
    public PayrollBean(long amount, String date, int user_id, Integer target_user_id, String payroll_type, String remark, String promote_income_type, int cumulative_day, long payroll_num) {
        this.payroll_amount = amount;
        this.transaction_time = date;
        this.user_id = user_id;
        this.target_user_id = target_user_id;
        this.type = payroll_type;
        this.transaction_remark = remark;
        this.promote_income_type = promote_income_type;
        this.cumulative_day = cumulative_day;
        this.remain_record = payroll_num;
    }
}

