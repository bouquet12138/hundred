package dy.network.hundred.service.impl;

import dy.network.hundred.dao.DeMachineNumDao;
import dy.network.hundred.java_bean.BaseBean;
import dy.network.hundred.service.DeMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("deMachineService")
public class DeMachineServiceImpl implements DeMachineService {

    @Autowired
    private DeMachineNumDao deMachineNumDao;

    @Override
    public BaseBean<Integer> getDemachineNum(Integer user_id) {
        BaseBean baseBean = new BaseBean();

        Integer num = deMachineNumDao.getDemachineNum(user_id);

        if (num == null) {
            baseBean.setMsg("获取失败");
            return baseBean;
        }

        baseBean.setCode(BaseBean.SUCCESS);
        baseBean.setMsg("获取成功");
        baseBean.setData(num);

        return baseBean;
    }

}
