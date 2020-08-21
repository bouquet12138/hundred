package dy.network.hundred.java_bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import dy.network.hundred.utils.DateUtil;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WithdrawBean {
    private Integer withdraw_id;
    private long withdraw_amount;
    private String withdraw_time;
    private Integer user_id;
    private Integer integral_id;
    private String withdraw_remark;
    private String bank_card;
    private String alipay;
    private String wechat;
    private long remain_record;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String pay_password;
    private String is_process;
    private String handle_time;
    private int payroll_id;


    public WithdrawBean(int amount, Integer user_id, String withdraw_remark, String bank_card, String alipay, String wechat, long remain_record) {
        //amount, user_id), withdraw_remark, bank_card, alipay, wechat, userBean.getWithdraw_num() + amount
        this.withdraw_amount=amount;
        this.user_id=user_id;
        this.withdraw_remark=withdraw_remark;
        this.bank_card=bank_card;
        this.alipay=alipay;
        this.wechat=wechat;
        this.remain_record = remain_record;
        this.withdraw_time = DateUtil.getDate();
    }
}


