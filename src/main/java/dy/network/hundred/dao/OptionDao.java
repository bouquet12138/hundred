package dy.network.hundred.dao;



import dy.network.hundred.java_bean.OptionBean;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository("optionDao")
public interface OptionDao {
  @Insert({"insert into option_tab(user_id,public_time,title,content) values(#{user_id},#{public_time},#{title},#{content})"})
  @Options(useGeneratedKeys = true, keyColumn = "option_id", keyProperty = "option_id")
  Integer userDeclareOneself(OptionBean paramOptionBean);
  
  @Select({"select * from option_tab"})
  List<OptionBean> findUserDeclareOneself();
}
