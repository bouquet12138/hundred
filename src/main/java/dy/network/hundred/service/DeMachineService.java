package dy.network.hundred.service;

import dy.network.hundred.java_bean.*;
import dy.network.hundred.java_bean.db_bean.DeMachineNumBean;
import dy.network.hundred.java_bean.db_bean.UserBean;

import java.util.List;


public interface DeMachineService {

    BaseBean<Integer> getDemachineNum(Integer user_id);


    BaseBean<Integer> rechargeDemachineNum(UserBean userBean);

    BaseBean scanStartMachine(UserBean userBean);

    BaseBean<List<DeMachineNumBean>> getDemachineNumList(PageBean pageBean);

    BaseBean modifyDemachineNum(DeMachineNumBean deMachineNumBean);
}
