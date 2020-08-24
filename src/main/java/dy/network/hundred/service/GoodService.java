package dy.network.hundred.service;



import dy.network.hundred.java_bean.DeliverNumBean;
import dy.network.hundred.java_bean.db_bean.GoodBean;
import dy.network.hundred.java_bean.db_bean.OrderRecordBean;
import dy.network.hundred.java_bean.db_bean.UserBean;
import dy.network.hundred.java_bean.BaseBean;
import java.util.List;

public interface GoodService {
  BaseBean<Integer> uploadGood(GoodBean paramGoodBean);
  
  BaseBean modifyGood(GoodBean paramGoodBean);
  
  BaseBean deleteGood(GoodBean paramGoodBean);
  
  BaseBean<List<GoodBean>> initGoodList(GoodBean paramGoodBean);
  
  BaseBean<List<GoodBean>> loadMoreGoodList(GoodBean paramGoodBean);
  
  BaseBean buyGood(OrderRecordBean paramOrderRecordBean);
  
  BaseBean<List<OrderRecordBean>> initOrderRecord(OrderRecordBean paramOrderRecordBean);
  
  BaseBean<List<OrderRecordBean>> loadMoreOrderRecord(OrderRecordBean paramOrderRecordBean);
  
  BaseBean<DeliverNumBean> getDeliverGoodNum(UserBean paramUserBean);
}
