package dy.network.hundred.controller;

import dy.network.hundred.java_bean.BaseBean;
import dy.network.hundred.java_bean.UserBean;
import dy.network.hundred.service.DeMachineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class DeMachineController {

    @Autowired
    private DeMachineService deMachineService;

    @PostMapping({"/get_demachine_num"})
    public BaseBean<Integer> getDemachineNum(@RequestBody UserBean userBean) {

        return deMachineService.getDemachineNum(userBean.getUser_id());
    }

}
