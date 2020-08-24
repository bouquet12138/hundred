package dy.network.hundred.dao;

import dy.network.hundred.dao.provider.ALGProvider;
import dy.network.hundred.dao.provider.UserDaoProvider;
import dy.network.hundred.java_bean.ALGBean;
import dy.network.hundred.java_bean.PageBean;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("algDao")
public interface ALGDao {

    @Select({"select * from alg_tab"})
    List<ALGBean> findAlgAll();

    @SelectProvider(type = ALGProvider.class, method = "Initializes")
    List<ALGBean> getAlgList(PageBean pageBean);

    @Update({"update alg_tab set num_a = #{num_a},num_b = #{num_b},add_money = #{add_money} where alg_id = #{alg_id}"})
    void modifyAlgInfo(ALGBean algBean);
}
