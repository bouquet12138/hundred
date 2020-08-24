package dy.network.hundred.controller;



import dy.network.hundred.java_bean.DeliverNumBean;
import dy.network.hundred.java_bean.db_bean.GoodBean;
import dy.network.hundred.java_bean.db_bean.OrderRecordBean;
import dy.network.hundred.java_bean.db_bean.UserBean;
import dy.network.hundred.service.GoodService;
import dy.network.hundred.java_bean.BaseBean;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;




@RestController
public class GoodController
{
  private static final Logger log = LoggerFactory.getLogger(GoodController.class);

  
  @Autowired
  private GoodService goodService;

  
  @PostMapping({"/upload_good"})
  public BaseBean uploadGood(@RequestBody GoodBean goodBean) {
    log.info(goodBean.toString());
    
    return this.goodService.uploadGood(goodBean);
  }

  
  @PostMapping({"/modify_good"})
  public BaseBean modifyGood(@RequestBody GoodBean goodBean) {
    log.info(goodBean.toString());
    
    return this.goodService.modifyGood(goodBean);
  }

  
  @PostMapping({"/delete_good"})
  public BaseBean deleteGood(@RequestBody GoodBean goodBean) {
    log.info(goodBean.toString());
    
    return this.goodService.deleteGood(goodBean);
  }

  
  @PostMapping({"/init_good_list"})
  public BaseBean<List<GoodBean>> initGoodList(@RequestBody GoodBean goodBean) {
    log.info(goodBean.toString());
    return this.goodService.initGoodList(goodBean);
  }


  
  @PostMapping({"/load_more_good_list"})
  public BaseBean<List<GoodBean>> loadMoreGoodList(@RequestBody GoodBean goodBean) { return this.goodService.loadMoreGoodList(goodBean); }



  
  @PostMapping({"/buy_good"})
  public BaseBean buyGood(@RequestBody OrderRecordBean orderRecordBean) { return this.goodService.buyGood(orderRecordBean); }



  
  @PostMapping({"/init_order_record"})
  public BaseBean<List<OrderRecordBean>> initOrderRecord(@RequestBody OrderRecordBean orderRecordBean) { return this.goodService.initOrderRecord(orderRecordBean); }



  
  @PostMapping({"/load_more_order_record"})
  public BaseBean<List<OrderRecordBean>> loadMoreOrderRecord(@RequestBody OrderRecordBean orderRecordBean) { return this.goodService.loadMoreOrderRecord(orderRecordBean); }



  
  @PostMapping({"/get_deliver_good_num"})
  public BaseBean<DeliverNumBean> getDeliverGoodNum(@RequestBody UserBean userBean) { return this.goodService.getDeliverGoodNum(userBean); }
}
