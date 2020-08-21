package dy.network.hundred.java_bean;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AppBean {
    private int update_status;
    private String app_md5;
    private Integer app_id;
    private String public_date;
    private String app_name;


    private String app_icon;
    private String app_url;
    private String app_describe;
    private String version_name;
    private long version_code;
    private long app_size;

}




