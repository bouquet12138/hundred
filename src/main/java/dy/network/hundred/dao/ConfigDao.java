package dy.network.hundred.dao;



import dy.network.hundred.dao.provider.ConfigProvider;
import dy.network.hundred.java_bean.db_bean.ConfigBean;

import java.util.List;

import dy.network.hundred.java_bean.PageBean;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository("configDao")
public interface ConfigDao {
    @Select({"select * from config_tab where config_name = #{ config_name }"})
    ConfigBean getConfigBeanWithName(String paramString);

    @Select({"select * from config_tab where config_status = #{ config_status }"})
    List<ConfigBean> getConfigBeanList(String paramString);

    @SelectProvider(type = ConfigProvider.class, method = "Initializes")
    List<ConfigBean> getConfigList(PageBean pageBean);

    @Update({"update config_tab set config_value = #{config_value} where config_id = #{config_id}"})
    void modifyConfigInfo(ConfigBean configBean);

}
