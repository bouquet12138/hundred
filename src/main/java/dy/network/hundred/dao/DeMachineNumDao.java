package dy.network.hundred.dao;

import dy.network.hundred.java_bean.DeMachineNumBean;
import dy.network.hundred.java_bean.DemachineBean;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

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

}
