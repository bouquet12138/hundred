package dy.network.hundred.controller;

import dy.network.hundred.java_bean.*;
import dy.network.hundred.java_bean.db_bean.DeMachineNumBean;
import dy.network.hundred.java_bean.db_bean.UserBean;
import dy.network.hundred.service.DeMachineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class DeMachineController {

    @Autowired
    private DeMachineService deMachineService;

    @PostMapping({"/get_demachine_num"})
    public BaseBean<Integer> getDemachineNum(@RequestBody UserBean userBean) {

        return deMachineService.getDemachineNum(userBean.getUser_id());
    }

    /**
     * 次数充值
     * @param userBean
     * @return
     */
    @PostMapping({"/recharge_demachine_num"})
    public BaseBean<Integer> rechargeDemachineNum(@RequestBody UserBean userBean) {

        return deMachineService.rechargeDemachineNum(userBean);
    }


    @PostMapping({"/scan_start_machine"})
    public BaseBean scanStartMachine(@RequestBody UserBean userBean) {

        return deMachineService.scanStartMachine(userBean);
    }


    @GetMapping({"/getDemachineNumList"})
    public BaseBean<List<DeMachineNumBean>> getDemachineNumList(Integer page, Integer limit, String name, String phone_num) {

        PageBean pageBean = new PageBean(page, limit, name, phone_num);

        return deMachineService.getDemachineNumList(pageBean);
    }


    @PostMapping({"/modify_demachine_num"})
    public BaseBean modifyDemachineNum(@RequestBody DeMachineNumBean deMachineNumBean) {
        return deMachineService.modifyDemachineNum(deMachineNumBean);
    }


}
