package dy.network.hundred.controller;


import dy.network.hundred.java_bean.StoreBean;
import dy.network.hundred.service.StoreService;
import dy.network.hundred.java_bean.BaseBean;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class StoreController
{
  private static final Logger log = LoggerFactory.getLogger(StoreController.class);
  
  @Autowired
  private StoreService storeService;
  
  @PostMapping({"/upload_store_info"})
  public BaseBean addStoreInformation(@RequestBody StoreBean storeBean) {
    BaseBean baseBean = this.storeService.addStoreInformation(storeBean);
    return baseBean;
  }
  
  @PostMapping({"/init_store_info"})
  public BaseBean<List<StoreBean>> InitializesTheStoreInformation(@RequestBody StoreBean storeBean) { return this.storeService.InitializesTheStoreInformation(storeBean); }

  
  @PostMapping({"/refresh_store_info"})
  public BaseBean<List<StoreBean>> RefreshStoreInformation(@RequestBody StoreBean storeBean) { return this.storeService.RefreshStoreInformation(storeBean); }

  
  @PostMapping({"/load_more_store_info"})
  public BaseBean<List<StoreBean>> DropDownToLoadMoreProductInformation(@RequestBody StoreBean storeBean) { return this.storeService.DropDownToLoadMoreProductInformation(storeBean); }
}
