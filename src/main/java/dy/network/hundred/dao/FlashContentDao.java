package dy.network.hundred.dao;



import dy.network.hundred.java_bean.FlashContentBean;
import javax.websocket.server.PathParam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

@Repository("flashContextDao")
public interface FlashContentDao {
  @Insert({"insert into flash_content_tab(flash_id,content,image_id) values(#{flash_id},#{content},#{image_id})"})
  Integer uploadFlashContent(FlashContentBean paramFlashContentBean);
  
  @Select({"select * from flash_content_tab where flash_id = #{flash_id}"})
  @Results(id = "flash_content_data", value = {@Result(property = "image", column = "image_id", one = @One(select = "dy.network.hundred.dao.ImageDao.findImageDDataByUserId", fetchType = FetchType.EAGER))})
  FlashContentBean initFlashContext(@PathParam("flash_id") Integer paramInteger);
}
