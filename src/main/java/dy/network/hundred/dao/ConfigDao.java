package dy.network.hundred.dao;



import dy.network.hundred.java_bean.ConfigBean;
import java.util.List;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository("configDao")
public interface ConfigDao {
  @Select({"select * from config_tab where config_name = #{ config_name }"})
  ConfigBean getConfigBeanWithName(String paramString);
  
  @Select({"select * from config_tab where config_status = #{ config_status }"})
  List<ConfigBean> getConfigBeanList(String paramString);
}
