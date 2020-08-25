package dy.network.hundred.service;



import dy.network.hundred.java_bean.PageBean;
import dy.network.hundred.java_bean.db_bean.PayrollBean;
import dy.network.hundred.java_bean.BaseBean;
import java.util.List;
import javax.websocket.server.PathParam;

public interface PayrollService {
  BaseBean<List<PayrollBean>> findUserSalaryInformation(@PathParam("user_id") Integer paramInteger);
  
  BaseBean<Long> findUserPayrollNum(@PathParam("user_id") Integer paramInteger);
  
  BaseBean transferAccounts(PayrollBean paramPayrollBean);
  
  BaseBean payrollToIntegral(PayrollBean paramPayrollBean);
  
  BaseBean sendPayroll();

  BaseBean<List<PayrollBean>> getPayrollList(PageBean pageBean);
}
