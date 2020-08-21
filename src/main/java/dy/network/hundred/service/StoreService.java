package dy.network.hundred.service;



import dy.network.hundred.java_bean.StoreBean;
import dy.network.hundred.java_bean.BaseBean;
import java.util.List;

public interface StoreService {
  BaseBean addStoreInformation(StoreBean paramStoreBean);
  
  BaseBean<List<StoreBean>> InitializesTheStoreInformation(StoreBean paramStoreBean);
  
  BaseBean<List<StoreBean>> RefreshStoreInformation(StoreBean paramStoreBean);
  
  BaseBean<List<StoreBean>> DropDownToLoadMoreProductInformation(StoreBean paramStoreBean);
}
