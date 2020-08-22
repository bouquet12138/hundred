package dy.network.hundred.java_bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import dy.network.hundred.utils.DateUtil;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserBean {

    public static final String NORMAL_USER = "普通会员";
    public static final String REGISTER_CENTER = "报单中心";

    public static final String ENABLE = "已激活";
    public static final String UN_ENABLE = "未激活";

    private Integer user_id;
    private String name;
    private String grade;
    private Integer head_img_id;
    private String phone_num;
    private String login_password;
    private String pay_password;
    private String sex;
    private String birthday;
    private String province;
    private String city;
    private String district;
    private String id_card;
    private String bank_card;
    private String recommend_user_phone;

    private Integer child_a;
    private Integer child_b;

    private int child_a_num;
    private int child_b_num;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String old_pay_password;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String new_pay_password;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String old_login_password;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String new_login_password;
    private String vertex_user_phone;
    private Integer is_merchant;
    private ImageBean head_img;
    private int buy_integral;
    private long integral_num;
    private long withdraw_num;
    private long payroll_num;

    private String register_time;
    private String login_time;

    private String enable;

    private String sn;

    public void setDefaultData() {
        sex = "男";
        register_time = DateUtil.getDate();

    }

    public void child_a_num_add() {
        child_a_num += 1;
    }

    public void child_b_num_add() {
        child_b_num += 1;
    }

}


