package dy.network.hundred.java_bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@JsonIgnoreProperties({"handler"})
public class ImageBean implements Serializable {
    private Integer image_id;
    private String image_name;
    private String image_url;


    private Integer width;
    private Integer height;
    private String image_type;
    private String image_describe;

    private String insert_time;//添加时间

}


