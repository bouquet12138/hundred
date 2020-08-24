package dy.network.hundred.service;



import dy.network.hundred.java_bean.db_bean.WithdrawBean;
import dy.network.hundred.java_bean.BaseBean;
import java.util.List;
import javax.websocket.server.PathParam;

public interface WithdrawService {
  BaseBean<Integer> userWithdrawal(WithdrawBean paramWithdrawBean);
  
  BaseBean<List<WithdrawBean>> findWithdrawDataByUid(@PathParam("user_id") Integer paramInteger);
  
  BaseBean<Integer> findUserWithdrawAmount(@PathParam("user_id") Integer paramInteger);
}
