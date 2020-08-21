package dy.network.hundred.java_bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonIgnoreProperties({"handler"})
public class GoodBean {
    public static final String SOLD_OUT = "0";
    public static final String SOLD_UP = "1";
    private Integer good_id;
    private String status;
    private String good_type;
    private String title;


    private String introduce;
    private int price;
    private String image_ids;
    private List<ImageBean> image_list;
    private String insert_time;
    private String update_time;


}

