package dy.network.hundred.dao;

import dy.network.hundred.java_bean.AdminBean;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository("adminDao")
public interface AdminDao {

    @Select({"select count(admin_id) from admin_tab where admin_account = #{admin_account} and admin_password = #{admin_password}"})
    Integer adminLogin(AdminBean adminBean);

}
