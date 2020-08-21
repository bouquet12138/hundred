package dy.network.hundred.dao;



import dy.network.hundred.java_bean.ImageBean;
import javax.websocket.server.PathParam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository("imageDao")
public interface ImageDao {
  @Select({"select * from image_tab where image_id = #{head_img_id}"})
  @Results(id = "image_data", value = {@Result(property = "image_id", column = "image_id", id = true), @Result(property = "image_url", column = "image_url"), @Result(property = "image_name", column = "image_name"), @Result(property = "image_type", column = "image_type"), @Result(property = "image_describe", column = "image_describe"), @Result(property = "width", column = "width"), @Result(property = "height", column = "height")})
  ImageBean findImageDDataByUserId(@PathParam("image_id") Integer paramInteger);
  
  @Insert({"insert into image_tab(image_url,image_name,image_type,image_describe,width,height,insert_time) values(#{image_url},#{image_name},#{image_type},#{image_describe},#{width},#{height},#{insert_time})"})
  @Options(keyColumn = "image_id", keyProperty = "image_id", useGeneratedKeys = true)
  Integer uploadImage(ImageBean paramImageBean);
}
