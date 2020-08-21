package dy.network.hundred.dao;



import dy.network.hundred.java_bean.AppBean;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository("appDao")
public interface AppDao {
  @Insert({"insert into app_tab(app_name,app_icon,app_url,app_describe,version_name,public_date,app_size,app_md5,version_code,update_status) values(#{app_name},#{app_icon},#{app_url},#{app_describe},#{version_name},#{public_date},#{app_size},#{app_md5},#{version_code},#{update_status})"})
  @Options(useGeneratedKeys = true, keyProperty = "app_id", keyColumn = "app_id")
  Integer addAppUpdateData(AppBean paramAppBean);
  
  @Select({"SELECT * FROM app_tab ORDER BY app_id DESC LIMIT 0,1"})
  AppBean findAppUpdateData();
}
