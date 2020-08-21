package dy.network.hundred.controller;

import dy.network.hundred.java_bean.WithdrawBean;
import dy.network.hundred.service.WithdrawService;
import dy.network.hundred.java_bean.BaseBean;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WithdrawController {
  
  @Autowired
  private WithdrawService withdrawService;

  
  @PostMapping({"/withdraw"})
  public BaseBean<Integer> userWithdrawal(@RequestBody WithdrawBean withdrawBean) { return this.withdrawService.userWithdrawal(withdrawBean); }


  
  @PostMapping({"/withdraw_record"})
  public BaseBean<List<WithdrawBean>> findWithdrawDataByUid(@RequestBody WithdrawBean withdrawBean) { return this.withdrawService.findWithdrawDataByUid(withdrawBean.getUser_id()); }


  
  @PostMapping({"/total_withdraw"})
  public BaseBean<Integer> findUserWithdrawAmount(@RequestBody WithdrawBean withdrawBean) { return this.withdrawService.findUserWithdrawAmount(withdrawBean.getUser_id()); }
}
