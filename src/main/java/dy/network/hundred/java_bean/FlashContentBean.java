package dy.network.hundred.java_bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Data
@JsonIgnoreProperties({"handler"})
public class FlashContentBean
        implements Serializable {
    private int flash_content_id;
    private int flash_id;
    private String content;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer image_id;
    private ImageBean image;


}

