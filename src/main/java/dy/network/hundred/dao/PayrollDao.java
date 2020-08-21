package dy.network.hundred.dao;

import dy.network.hundred.java_bean.PayrollBean;
import dy.network.hundred.java_bean.UserBean;

import java.util.List;
import javax.websocket.server.PathParam;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

@Repository("payrollDao")
public interface PayrollDao {
    @Insert({"insert into payroll_tab(payroll_amount,transaction_time,user_id,type,transaction_remark,promote_income_type,cumulative_day,remain_record,target_user_id) values(#{payroll_amount},#{transaction_time},#{user_id},#{type},#{transaction_remark},#{promote_income_type},#{cumulative_day},#{remain_record},#{target_user_id})"})
    @Options(useGeneratedKeys = true, keyProperty = "payroll_id", keyColumn = "payroll_id")
    Integer SalaryInformation(PayrollBean paramPayrollBean);

    @Select({"select * from payroll_tab where user_id = #{user_id}"})
    @Results(id = "payrollUserdata", value = {@Result(property = "targetUserBean", column = "target_user_id", one = @One(select = "dy.network.hundred.dao.UserDao.findUserDataByUserId", fetchType = FetchType.EAGER))})
    List<PayrollBean> findUserSalaryInformation(@PathParam("user_id") Integer paramInteger);

    @Select({"select * from payroll_tab where user_id = #{user_id} and type = 'promote_income' ORDER BY payroll_id DESC LIMIT 0,1"})
    PayrollBean findUserSalaryInformationByUidAndType(@PathParam("user_id") Integer paramInteger);

    @Select({"select * from user_tab where user_id = #{user_id}"})
    UserBean findUserDataByUserId(@PathParam("user_id") Integer paramInteger);

    @Select({"select * from payroll_tab where transaction_time LIKE CONCAT('%',#{transaction_time},'%') and type = #{type}"})
    List<PayrollBean> isSendPayroll(PayrollBean paramPayrollBean);
}
