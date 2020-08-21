package dy.network.hundred.dao;


import dy.network.hundred.java_bean.FlashBean;

import java.util.List;
import javax.websocket.server.PathParam;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

@Repository("flashDao")
public interface FlashDao {
    @Insert({"insert into flash_tab(user_id,title,reading_volume,public_time) values(#{user_id},#{title},#{reading_volume},#{public_time})"})
    @Options(keyColumn = "flash_id", keyProperty = "flash_id", useGeneratedKeys = true)
    Integer uploadFlash(FlashBean paramFlashBean);

    @Select({"SELECT * FROM flash_tab where  type = #{type} ORDER BY flash_id DESC LIMIT 0,10"})
    @Results(id = "flashData", value = {@Result(id = true, column = "flash_id", property = "flash_id"), @Result(property = "content_list", column = "flash_id", many = @Many(select = "dy.network.hundred.dao.FlashContentDao.initFlashContext", fetchType = FetchType.LAZY))})
    List<FlashBean> initFlash(FlashBean flashBean);

    @Select({"select * from flash_tab where flash_id > #{flash_id} and type = #{type}"})
    @ResultMap({"flashData"})
    List<FlashBean> refreshFlash(FlashBean flashBean);

    @Select({"select * from flash_tab where flash_id < #{flash_id} and  type = #{type} ORDER BY flash_id DESC LIMIT 0,10"})
    @ResultMap({"flashData"})
    List<FlashBean> loadMoreFlash(FlashBean flashBean);

    @Update({"update flash_tab set reading_volume = reading_volume + 1 where flash_id = #{flash_id}"})
    Integer addFlashReadingVolume(@PathParam("flash_id") int paramInt);
}
