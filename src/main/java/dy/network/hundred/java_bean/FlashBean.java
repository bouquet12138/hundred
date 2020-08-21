package dy.network.hundred.java_bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@JsonIgnoreProperties({"handler"})
public class FlashBean
        implements Serializable {

    public static final String FLASH = "快讯";
    public static final String ADVERT = "广告";

    private int flash_id;
    private Integer user_id;
    private String title;
    private long reading_volume;

    private String type;

    private String public_time;
    private List<FlashContentBean> content_list;

    @Override
    public String toString() {
        return "FlashBean{" +
                "flash_id=" + flash_id +
                ", user_id=" + user_id +
                ", title='" + title + '\'' +
                ", reading_volume=" + reading_volume +
                ", public_time='" + public_time + '\'' +
                ", content_list=" + content_list +
                '}';
    }
}


