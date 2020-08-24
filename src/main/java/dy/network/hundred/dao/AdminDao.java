package dy.network.hundred.dao;

import dy.network.hundred.java_bean.db_bean.AdminBean;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository("adminDao")
public interface AdminDao {

    @Select({"select count(admin_id) from admin_tab where admin_account = #{admin_account} and admin_password = #{admin_password}"})
    Integer adminLogin(AdminBean adminBean);

    /**
     * 管理支付密码是否正确
     * @param adminBean
     * @return
     */
    @Select({"select count(admin_id) from admin_tab where admin_pay_password = #{admin_pay_password}"})
    Integer adminPayPass(AdminBean adminBean);

}
