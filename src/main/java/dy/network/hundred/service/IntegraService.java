package dy.network.hundred.service;



import dy.network.hundred.java_bean.IntegralBean;
import dy.network.hundred.java_bean.BaseBean;
import java.util.List;
import javax.websocket.server.PathParam;

public interface IntegraService {
  BaseBean TransferToEachOther(IntegralBean paramIntegralBean);
  
  BaseBean<List<IntegralBean>> findIntegralDataByUid(@PathParam("user_id") Integer paramInteger);
  
  BaseBean<Long> findUserIntegralByUserID(@PathParam("user_id") Integer paramInteger);
  
  BaseBean<Long> integralToPayroll(IntegralBean paramIntegralBean);
}
