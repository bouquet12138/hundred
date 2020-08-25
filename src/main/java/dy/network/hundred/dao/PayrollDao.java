package dy.network.hundred.dao;

import dy.network.hundred.dao.provider.IntegralProvider;
import dy.network.hundred.dao.provider.PayrollProvider;
import dy.network.hundred.java_bean.PageBean;
import dy.network.hundred.java_bean.db_bean.PayrollBean;
import dy.network.hundred.java_bean.db_bean.UserBean;

import java.util.List;
import javax.websocket.server.PathParam;

import org.apache.ibatis.annotations.*;

import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

@Repository("payrollDao")
public interface PayrollDao {
    @Insert({"insert into payroll_tab(payroll_amount,transaction_time,user_id,type,transaction_remark,promote_income_type,cumulative_day,remain_record,target_user_id) values(#{payroll_amount},#{transaction_time},#{user_id},#{type},#{transaction_remark},#{promote_income_type},#{cumulative_day},#{remain_record},#{target_user_id})"})
    @Options(useGeneratedKeys = true, keyProperty = "payroll_id", keyColumn = "payroll_id")
    Integer SalaryInformation(PayrollBean paramPayrollBean);

    @Select({"select * from payroll_tab where user_id = #{user_id}"})
    @Results(id = "payrollTargetUserdata", value = {@Result(property = "targetUserBean", column = "target_user_id", one = @One(select = "dy.network.hundred.dao.UserDao.findUserDataByUserId", fetchType = FetchType.EAGER))})
    List<PayrollBean> findUserSalaryInformation(@PathParam("user_id") Integer paramInteger);

    @Select({"select * from payroll_tab where user_id = #{user_id} and type = 'promote_income' ORDER BY payroll_id DESC LIMIT 0,1"})
    PayrollBean findUserSalaryInformationByUidAndType(@PathParam("user_id") Integer paramInteger);

    @Select({"select * from user_tab where user_id = #{user_id}"})
    UserBean findUserDataByUserId(@PathParam("user_id") Integer paramInteger);

    @Select({"select * from payroll_tab where transaction_time LIKE CONCAT('%',#{transaction_time},'%') and type = #{type}"})
    List<PayrollBean> isSendPayroll(PayrollBean paramPayrollBean);

    @SelectProvider(type = PayrollProvider.class, method = "Initializes")
    @Results(id = "payrollUserdata", value = {@Result(property = "userBean",
            column = "user_id", one = @One(select = "dy.network.hundred.dao.UserDao.findUserDataByUserIdNoImage", fetchType = FetchType.EAGER))})
    List<PayrollBean> getPayrollList(PageBean pageBean);
}
