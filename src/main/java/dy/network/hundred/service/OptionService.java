package dy.network.hundred.service;



import dy.network.hundred.java_bean.OptionBean;
import dy.network.hundred.java_bean.BaseBean;
import java.util.List;

public interface OptionService {
  BaseBean<Integer> userDeclareOneself(OptionBean paramOptionBean);
  
  BaseBean<List<OptionBean>> findUserDeclareOneself();
}
