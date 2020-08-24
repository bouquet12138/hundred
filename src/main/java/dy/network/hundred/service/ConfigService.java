package dy.network.hundred.service;

import dy.network.hundred.java_bean.BaseBean;
import dy.network.hundred.java_bean.db_bean.ConfigBean;
import dy.network.hundred.java_bean.PageBean;

import java.util.List;

public interface ConfigService {

    BaseBean<List<ConfigBean>> getConfigList(PageBean pageBean);

    BaseBean modifyConfigInfo(ConfigBean configBean);

}
