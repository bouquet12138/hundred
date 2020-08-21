package dy.network.hundred.dao;

import dy.network.hundred.java_bean.WithdrawBean;
import java.util.List;
import javax.websocket.server.PathParam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository("withdrawDao")
public interface WithdrawDao {
  @Insert({"insert into withdraw_tab(withdraw_amount,withdraw_time,user_id,payroll_id,withdraw_remark,bank_card,alipay,wechat,remain_record) values(#{withdraw_amount},#{withdraw_time},#{user_id},#{payroll_id},#{withdraw_remark},#{bank_card},#{alipay},#{wechat},#{remain_record})"})
  @Options(useGeneratedKeys = true, keyProperty = "withdraw_id", keyColumn = "withdraw_id")
  Integer userWithdrawal(WithdrawBean paramWithdrawBean);
  
  @Select({"select * from withdraw_tab where user_id = #{user_id}"})
  List<WithdrawBean> findWithdrawDataByUid(@PathParam("user_id") Integer paramInteger);
  
  @Select({"select sum(withdraw_amount) from withdraw_tab where user_id = #{user_id}"})
  Integer findUserWithdrawAmount(@PathParam("user_id") Integer paramInteger);
}
