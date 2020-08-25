package dy.network.hundred.controller;

import dy.network.hundred.java_bean.PageBean;
import dy.network.hundred.java_bean.db_bean.PayrollBean;
import dy.network.hundred.java_bean.db_bean.WithdrawBean;
import dy.network.hundred.service.WithdrawService;
import dy.network.hundred.java_bean.BaseBean;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WithdrawController {

    @Autowired
    private WithdrawService withdrawService;


    @PostMapping({"/withdraw"})
    public BaseBean<Integer> userWithdrawal(@RequestBody WithdrawBean withdrawBean) {
        return this.withdrawService.userWithdrawal(withdrawBean);
    }


    @PostMapping({"/withdraw_record"})
    public BaseBean<List<WithdrawBean>> findWithdrawDataByUid(@RequestBody WithdrawBean withdrawBean) {
        return this.withdrawService.findWithdrawDataByUid(withdrawBean.getUser_id());
    }


    @PostMapping({"/total_withdraw"})
    public BaseBean<Integer> findUserWithdrawAmount(@RequestBody WithdrawBean withdrawBean) {
        return this.withdrawService.findUserWithdrawAmount(withdrawBean.getUser_id());
    }


    @GetMapping({"/getWithBeanList"})
    public BaseBean<List<WithdrawBean>> getWithBeanList(Integer page, Integer limit, String name, String phone_num) {
        PageBean pageBean = new PageBean(page, limit, name, phone_num);
        return withdrawService.getWithBeanList(pageBean);
    }


    @PostMapping({"/handle_withdraw"})
    public BaseBean handleWithdraw(@RequestBody WithdrawBean withdrawBean) {
        return withdrawService.handleWithdraw(withdrawBean);
    }

}
