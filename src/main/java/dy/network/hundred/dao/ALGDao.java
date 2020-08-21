package dy.network.hundred.dao;

import dy.network.hundred.java_bean.ALGBean;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("algDao")
public interface ALGDao {

    @Select({"select * from alg_tab"})
    List<ALGBean> findAlgAll();

}
