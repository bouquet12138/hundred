package dy.network.hundred.service;



import dy.network.hundred.java_bean.PageBean;
import dy.network.hundred.java_bean.db_bean.OptionBean;
import dy.network.hundred.java_bean.BaseBean;
import dy.network.hundred.java_bean.db_bean.UserBean;

import java.util.List;

public interface OptionService {
  BaseBean<Integer> userDeclareOneself(OptionBean paramOptionBean);
  
  BaseBean<List<OptionBean>> findUserDeclareOneself();

  BaseBean<List<OptionBean>> getOptionList(PageBean pageBean);
}
