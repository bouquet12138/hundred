package dy.network.hundred.service.impl;

import dy.network.hundred.dao.AdminDao;
import dy.network.hundred.java_bean.db_bean.AdminBean;
import dy.network.hundred.java_bean.BaseBean;
import dy.network.hundred.service.AdminService;
import dy.network.hundred.utils.MD5Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service("adminService")
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;

    @Override
    public BaseBean adminLogin(AdminBean adminBean) {

        BaseBean baseBean = new BaseBean();

        adminBean.setAdmin_password(MD5Utils.generateMD5(adminBean.getAdmin_password()));//加密支付密码
        log.info(adminBean.getAdmin_password());

        int result = adminDao.adminLogin(adminBean);

        if (result <= 0) {
            baseBean.setMsg("账号或密码错误");
            return baseBean;
        }

        baseBean.setCode(BaseBean.SUCCESS);
        baseBean.setMsg("登录成功");

        return baseBean;
    }
}
