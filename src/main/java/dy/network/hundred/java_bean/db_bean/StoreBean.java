package dy.network.hundred.java_bean.db_bean;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class StoreBean {
    private int store_id;
    private String store_name;
    private Integer head_img_id;
    private String store_type;
    private String province;
    private String city;
    private String district;
    private String detailed_address;


    private double longitude;
    private double latitude;
    private String business_hours;
    private String store_describe;
    private String contact_phone;
    private String product_img_ids;
    private String public_time;
    private ImageBean head_img;
    private List<ImageBean> product_imgs;


}


