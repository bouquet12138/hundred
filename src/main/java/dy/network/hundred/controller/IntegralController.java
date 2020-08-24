package dy.network.hundred.controller;


import dy.network.hundred.java_bean.db_bean.IntegralBean;
import dy.network.hundred.service.IntegraService;
import dy.network.hundred.java_bean.BaseBean;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class IntegralController {
    private static final Logger log = LoggerFactory.getLogger(IntegralController.class);


    @Autowired
    private IntegraService integralService;


    @PostMapping({"/integral_rotation"})
    public BaseBean TransferToEachOther(@RequestBody IntegralBean integralBean) {
        return integralService.TransferToEachOther(integralBean);
    }


    @PostMapping({"/total_integral_record"})
    public BaseBean<List<IntegralBean>> findIntegralDataByUid(@RequestBody IntegralBean integralBean) {
        return integralService.findIntegralDataByUid(integralBean.getUser_id());
    }


    @PostMapping({"/total_integral"})
    public BaseBean<Long> findUserIntegralByUserID(@RequestBody IntegralBean integralBean) {
        return integralService.findUserIntegralByUserID(integralBean.getUser_id());
    }


    @PostMapping({"/integral_to_payroll"})
    public BaseBean<Long> integralToPayroll(@RequestBody IntegralBean integralBean) {
        return integralService.integralToPayroll(integralBean);
    }

    /**
     * 为用户充积分
     * @param integralBean
     * @return
     */
    @PostMapping({"/recharge_integral_for_user"})
    public BaseBean rechargeIntegralForUser(@RequestBody IntegralBean integralBean) {
        return integralService.rechargeIntegralForUser(integralBean);
    }

}
