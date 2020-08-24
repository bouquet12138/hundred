package dy.network.hundred.service.impl;

import dy.network.hundred.dao.ConfigDao;
import dy.network.hundred.java_bean.BaseBean;
import dy.network.hundred.java_bean.db_bean.ConfigBean;
import dy.network.hundred.java_bean.PageBean;
import dy.network.hundred.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("configService")
public class ConfigServiceImpl implements ConfigService {

    @Autowired
    private ConfigDao configDao;

    @Override
    public BaseBean<List<ConfigBean>> getConfigList(PageBean pageBean) {
        BaseBean<List<ConfigBean>> baseBean = new BaseBean<>();
        List<ConfigBean> configBeanList = configDao.getConfigList(pageBean);

        baseBean.setMsg("加载成功");
        baseBean.setCode(BaseBean.SUCCESS);//成功
        baseBean.setData(configBeanList);

        return baseBean;
    }

    @Override
    public BaseBean modifyConfigInfo(ConfigBean configBean) {
        BaseBean baseBean = new BaseBean<>();

        configDao.modifyConfigInfo(configBean);

        baseBean.setCode(BaseBean.SUCCESS);//成功
        baseBean.setMsg("修改成功");

        return baseBean;
    }
}
