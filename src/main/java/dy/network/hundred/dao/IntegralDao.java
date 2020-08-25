package dy.network.hundred.dao;


import dy.network.hundred.dao.provider.IntegralProvider;
import dy.network.hundred.java_bean.PageBean;
import dy.network.hundred.java_bean.db_bean.IntegralBean;

import java.util.List;
import javax.websocket.server.PathParam;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

@Repository("integralDao")
public interface IntegralDao {
    @Insert({"insert into integral_tab(transaction_amount,transaction_time,user_id,target_user_id,integral_type," +
            "transaction_remark,remain_record) values(#{transaction_amount},#{transaction_time},#{user_id},#{target_user_id},#{integral_type},#{transaction_remark},#{remain_record})"})
    @Options(useGeneratedKeys = true, keyColumn = "integral_id", keyProperty = "integral_id")
    Integer addIntegralData(IntegralBean paramIntegralBean);

    @Select({"select * from integral_tab where user_id = #{user_id}"})
    @Results(id = "integralTargetUserdata", value = {@Result(property = "targetUserBean",
            column = "target_user_id", one = @One(select = "dy.network.hundred.dao.UserDao.findUserDataByUserIdNoImage", fetchType = FetchType.EAGER))})
    List<IntegralBean> findIntegralDataByUid(@PathParam("user_id") Integer paramInteger);

    @Select({"select * from integral_tab where  DATEDIFF(#{transaction_time}, transaction_time) = #{day}" +
            " and integral_type = #{integral_type}"})
    List<IntegralBean> findIntegralBeanCount(int day, String transaction_time, String integral_type);

    @Select({"select * from integral_tab where integral_type = #{integral_type}"})
    List<IntegralBean> findTotalIntegralBean( String integral_type);

    @SelectProvider(type = IntegralProvider.class, method = "Initializes")
    @Results(id = "integralUserdata", value = {@Result(property = "userBean",
            column = "user_id", one = @One(select = "dy.network.hundred.dao.UserDao.findUserDataByUserIdNoImage", fetchType = FetchType.EAGER))})
    List<IntegralBean> getIntegralList(PageBean pageBean);
}
