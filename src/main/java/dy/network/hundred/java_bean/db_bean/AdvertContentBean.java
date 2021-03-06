package dy.network.hundred.java_bean.db_bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@JsonIgnoreProperties({"handler"})
@Data
public class AdvertContentBean
        implements Serializable {
    private int advert_content_id;
    private int advert_id;
    private String content;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer image_id;
    private ImageBean image;

}


