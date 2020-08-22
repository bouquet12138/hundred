package dy.network.hundred.service;

import dy.network.hundred.java_bean.BaseBean;
import dy.network.hundred.java_bean.UserBean;


public interface DeMachineService {

    BaseBean<Integer> getDemachineNum(Integer user_id);


    BaseBean<Integer> rechargeDemachineNum(UserBean userBean);

    BaseBean scanStartMachine(UserBean userBean);
}
