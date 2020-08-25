package dy.network.hundred.dao;

import dy.network.hundred.dao.provider.WithdrawProvider;
import dy.network.hundred.java_bean.PageBean;
import dy.network.hundred.java_bean.db_bean.WithdrawBean;

import java.util.List;
import javax.websocket.server.PathParam;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
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

    @SelectProvider(type = WithdrawProvider.class, method = "Initializes")
    @Results(id = "withdrawUserdata", value = {@Result(property = "userBean",
            column = "user_id", one = @One(select = "dy.network.hundred.dao.UserDao.findUserDataByUserIdNoImage", fetchType = FetchType.EAGER))})
    List<WithdrawBean> getWithBeanList(PageBean pageBean);

    /**
     * 更新状态
     *
     * @param withdrawBean
     */
    @Update({"update withdraw_tab set is_process = #{is_process} where withdraw_id = #{withdraw_id}"})
    void handleWithdraw(WithdrawBean withdrawBean);
}
