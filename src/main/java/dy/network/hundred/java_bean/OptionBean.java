package dy.network.hundred.java_bean;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OptionBean {
    private Integer option_id;
    private Integer user_id;


    private String public_time;
    private String title;
    private String content;


}


