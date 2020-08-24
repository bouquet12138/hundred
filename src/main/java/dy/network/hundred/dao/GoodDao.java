package dy.network.hundred.dao;



import dy.network.hundred.dao.provider.GoodDaoProvider;
import dy.network.hundred.java_bean.db_bean.GoodBean;
import dy.network.hundred.java_bean.db_bean.OrderRecordBean;
import java.util.List;
import javax.websocket.server.PathParam;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

@Repository("goodDao")
public interface GoodDao {
  @Insert({"insert into good_tab(good_type,title,introduce,price,image_ids,insert_time) values(#{good_type}, #{title},#{introduce},#{price},#{image_ids},#{insert_time})"})
  @Options(useGeneratedKeys = true, keyProperty = "good_id", keyColumn = "good_id")
  Integer addGoodInformation(GoodBean paramGoodBean);
  
  @Update({"update good_tab set good_type=#{good_type},status=#{status},title=#{title},introduce=#{introduce},price=#{price},image_ids=#{image_ids} where good_id = #{good_id}"})
  Integer modifyGood(GoodBean paramGoodBean);
  
  @Delete({"delete from good_tab where good_id = #{good_id}"})
  Integer deleteGood(GoodBean paramGoodBean);
  
  @SelectProvider(type = GoodDaoProvider.class, method = "initializes")
  List<GoodBean> initGoodList(GoodBean paramGoodBean);
  
  @Select({"select * from good_tab where good_id = #{good_id}"})
  GoodBean findGoodById(int paramInt);
  
  @Insert({"insert into order_record_tab(user_id,good_id,good_type,serial,status,good_count,receive_address,receive_detail_address,receive_name,receive_phone,insert_time) values(#{user_id}, #{good_id},#{good_type},#{serial},#{status},#{good_count},#{receive_address},#{receive_detail_address},#{receive_name},#{receive_phone},#{insert_time})"})
  @Options(useGeneratedKeys = true, keyProperty = "order_id", keyColumn = "order_id")
  Integer addBuyGoodRecord(OrderRecordBean paramOrderRecordBean);
  
  @Select({"select count(*) from order_record_tab where serial = #{serial}"})
  OrderRecordBean findOrderWithSerial(@PathParam("serial") String paramString);
  
  @SelectProvider(type = GoodDaoProvider.class, method = "init_order_record")
  @Results(id = "good_data", value = {@Result(property = "good_bean", column = "good_id", one = @One(select = "dy.network.hundred.dao.GoodDao.findGoodById", fetchType = FetchType.EAGER))})
  List<OrderRecordBean> initOrderRecord(OrderRecordBean paramOrderRecordBean);
  
  @Select({"select count(*) from order_record_tab where user_id = #{user_id} and status = #{status}"})
  int getDelivery(OrderRecordBean paramOrderRecordBean);
  
  @Select({"select * from order_record_tab where status = #{status}"})
  List<OrderRecordBean> getOrderList(@PathParam("status") String paramString);
  
  @Update({"update order_record_tab set status=#{status} where order_id = #{order_id}"})
  void updateOrderRecordStatus(OrderRecordBean paramOrderRecordBean);
  
  @Select({"select * from good_tab"})
  List<GoodBean> getGoodList();
  
  @Update({"update good_tab set image_ids=#{image_ids} where good_id = #{good_id}"})
  void updateImageIds(GoodBean paramGoodBean);
}
