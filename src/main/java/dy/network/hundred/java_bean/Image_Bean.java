package dy.network.hundred.java_bean;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Image_Bean {
    private Integer image_id;
    private String image_name;
    private Integer width;


    private Integer height;
    private String image_type;
    private String image_describe;
}


