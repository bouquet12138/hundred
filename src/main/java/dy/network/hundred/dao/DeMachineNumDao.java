package dy.network.hundred.dao;

import dy.network.hundred.dao.provider.DeMachineNumProvider;
import dy.network.hundred.java_bean.db_bean.DeMachineNumBean;
import dy.network.hundred.java_bean.db_bean.DemachineBean;
import dy.network.hundred.java_bean.PageBean;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("deMachineNumDao")
public interface DeMachineNumDao {

    @Insert({"insert into demachine_num_tab(demachine_num,user_id,insert_time)" +
            " values(#{demachine_num},#{user_id},#{insert_time})"})
    @Options(useGeneratedKeys = true, keyColumn = "demachine_num_id", keyProperty = "demachine_num_id")
    Integer addDeMachineNumData(DeMachineNumBean deMachineNumBean);

    @Select("select demachine_num from demachine_num_tab where user_id = #{user_id}")
    Integer getDemachineNum(Integer user_id);

    @Select("select * from demachine_num_tab where user_id = #{user_id}")
    DeMachineNumBean findDeMachineNumByUserId(int user_id);


    @Update({"update demachine_num_tab set demachine_num = #{demachine_num},update_time = #{update_time} where user_id = #{user_id}"})
    void updateDeMachineNumBean(DeMachineNumBean deMachineNumBean);

    @Select("select * from demachine_tab where sn = #{sn}")
    DemachineBean findDeMachineBySn(String sn);

    @SelectProvider(type = DeMachineNumProvider.class, method = "Initializes")
    @Results(id = "demachineNumUserdata", value = {@Result(property = "userBean",
            column = "user_id", one = @One(select = "dy.network.hundred.dao.UserDao.findUserDataByUserIdNoImage", fetchType = FetchType.EAGER))})
    List<DeMachineNumBean> getDemachineNumList(PageBean pageBean);

    @Update({"update demachine_num_tab set demachine_num = #{demachine_num} where demachine_num_id = #{demachine_num_id}"})
    void modifyDemachineNum(DeMachineNumBean deMachineNumBean);
}
