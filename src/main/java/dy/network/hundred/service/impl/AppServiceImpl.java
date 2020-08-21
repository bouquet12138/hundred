package dy.network.hundred.service.impl;

import dy.network.hundred.dao.AppDao;
import dy.network.hundred.java_bean.AppBean;
import dy.network.hundred.service.AppService;
import dy.network.hundred.java_bean.BaseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static dy.network.hundred.java_bean.BaseBean.SUCCESS;


@Service("appService")
public class AppServiceImpl
        implements AppService {

    @Autowired
    private AppDao appDao;

    public BaseBean addAppUpdateData(AppBean appBean) {
        BaseBean baseBean = new BaseBean();
        Integer data = this.appDao.addAppUpdateData(appBean);

        if (data > 0) {
            baseBean.setCode(SUCCESS);
            baseBean.setMsg("上传成功");
        } else {
            baseBean.setMsg("上传失败");
        }

        return baseBean;
    }


    public BaseBean<AppBean> findAppUpdateData() {
        BaseBean<AppBean> baseBean = new BaseBean();
        AppBean appBeans = this.appDao.findAppUpdateData();
        if (appBeans != null) {
            baseBean.setCode(SUCCESS);
            baseBean.setMsg("信息获取成功");
            baseBean.setData(appBeans);
        } else {
            baseBean.setMsg("信息获取失败");
        }
        return baseBean;
    }
}

