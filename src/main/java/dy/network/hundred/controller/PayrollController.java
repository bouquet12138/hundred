package dy.network.hundred.controller;

import dy.network.hundred.java_bean.PayrollBean;
import dy.network.hundred.service.PayrollService;
import dy.network.hundred.java_bean.BaseBean;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class PayrollController
{
  private static final Logger log = LoggerFactory.getLogger(PayrollController.class);

  
  @Autowired
  private PayrollService payrollService;

  
  @PostMapping({"/payroll_record"})
  public BaseBean<List<PayrollBean>> findUserSalaryInformation(@RequestBody PayrollBean payrollBean) { return this.payrollService.findUserSalaryInformation(payrollBean.getUser_id()); }



  
  @PostMapping({"/total_payroll"})
  public BaseBean<Long> findUserPayrollNum(@RequestBody PayrollBean payrollBean) { return this.payrollService.findUserPayrollNum(payrollBean.getUser_id()); }



  
  @PostMapping({"/payroll_rotation"})
  public BaseBean transferAccounts(@RequestBody PayrollBean payrollBean) { return this.payrollService.transferAccounts(payrollBean); }



  
  @PostMapping({"/payroll_to_integral"})
  public BaseBean payrollToIntegral(@RequestBody PayrollBean payrollBean) { return this.payrollService.payrollToIntegral(payrollBean); }








  
  @PostMapping({"/send_payroll"})
  public BaseBean sendPayroll() { return this.payrollService.sendPayroll(); }
}
