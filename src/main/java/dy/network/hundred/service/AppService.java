package dy.network.hundred.service;



import dy.network.hundred.java_bean.AppBean;
import dy.network.hundred.java_bean.BaseBean;

public interface AppService {
  BaseBean addAppUpdateData(AppBean paramAppBean);
  
  BaseBean<AppBean> findAppUpdateData();
}
