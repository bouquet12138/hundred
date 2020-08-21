
package dy.network.hundred.java_bean;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
 import java.util.List;

@NoArgsConstructor
@JsonIgnoreProperties({"handler"})
@Data
 public class AdvertBean implements Serializable {
       private int advert_id;
       private Integer user_id;
       private String title;
       private long reading_volume;
       private String public_time;
       private List<AdvertContentBean> content_list;
}
