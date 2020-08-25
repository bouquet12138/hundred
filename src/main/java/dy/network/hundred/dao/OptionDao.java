package dy.network.hundred.dao;


import dy.network.hundred.dao.provider.OptionProvider;
import dy.network.hundred.java_bean.PageBean;
import dy.network.hundred.java_bean.db_bean.OptionBean;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

@Repository("optionDao")
public interface OptionDao {
  @Insert({"insert into option_tab(user_id,public_time,title,content) values(#{user_id},#{public_time},#{title},#{content})"})
  @Options(useGeneratedKeys = true, keyColumn = "option_id", keyProperty = "option_id")
  Integer userDeclareOneself(OptionBean paramOptionBean);
  
  @Select({"select * from option_tab"})
  List<OptionBean> findUserDeclareOneself();

  @SelectProvider(type = OptionProvider.class, method = "Initializes")
  @Results(id = "optionUserdata", value = {@Result(property = "userBean",
          column = "user_id", one = @One(select = "dy.network.hundred.dao.UserDao.findUserDataByUserIdNoImage", fetchType = FetchType.EAGER))})
  List<OptionBean> getOptionList(PageBean pageBean);
}
