package dy.network.hundred.dao;


import dy.network.hundred.dao.provider.UserDaoProvider;
import dy.network.hundred.java_bean.PageBean;
import dy.network.hundred.java_bean.db_bean.UserBean;

import java.util.List;
import javax.websocket.server.PathParam;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public interface UserDao {

    @Select({"select * from user_tab"})
    List<UserBean> findUserAll();

    @Select({"select count(user_id) from user_tab where phone_num = #{phone_num} and login_password = #{login_password}"})
    Integer userLogin(UserBean paramUserBean);

    @Insert({"insert into user_tab(phone_num,login_password,pay_password,recommend_user_phone,register_time,child_a,child_b,is_merchant,integral_num,withdraw_num,payroll_num)" +
            "values(#{phone_num},#{login_password},#{pay_password},#{recommend_user_phone},#{register_time},#{child_a},#{child_b},#{is_merchant},#{integral_num},#{withdraw_num},#{payroll_num})"})
    @Options(useGeneratedKeys = true, keyProperty = "user_id", keyColumn = "user_id")
    Integer userRegister(UserBean paramUserBean);

    @Select({"select has_three from user_tab where phone_num = #{phone_num}"})
    String getUserZoneNumberByPhoneNum(@PathParam("phone_num") String paramString);

    @Select({"select has_three from user_tab where user_id = #{user_id}"})
    String getUserZoneNumberByUid(@PathParam("user_id") Integer paramInteger);

    @Select({"select count(phone_num) from user_tab where phone_num = #{phone_num}"})
    Integer userPhoneExists(@PathParam("phone_num") String paramString);

    @Select({"select * from user_tab where phone_num = #{recommend_user_phone}"})
    UserBean findUserPartByPhone_num(@PathParam("recommend_user_phone") String paramString);

    @Delete({"delete from user_tab where user_id = #{user_id}"})
    Integer deleteUserByUid(@PathParam("user_id") Integer paramInteger);

    @Select({"select count(*) from user_tab where vertex_user_phone = #{vertex_user_phone}"})
    Integer findCountByVertexUserPhone(@PathParam("vertex_user_phone") String paramString);

    @Update({"update user_tab set name = #{name},sex = #{sex},birthday = #{birthday},province = #{province},city = #{city},district = #{district},id_card = #{id_card},bank_card = #{bank_card},head_img_id = #{head_img_id} where user_id = #{user_id}"})
    Integer updateUserDataByUserId(UserBean paramUserBean);

    @Update({"update user_tab set login_password = #{new_login_password} where user_id = #{user_id}"})
    Integer updateUserLoginPassword(UserBean paramUserBean);

    @Update({"update user_tab set pay_password = #{new_pay_password} where user_id = #{user_id}"})
    Integer updateUserPayPassword(UserBean paramUserBean);

    @Select({"select * from user_tab where user_id = #{user_id}"})
    UserBean findUserDataByUserIdNoImage(@PathParam("user_id") Integer paramInteger);

    @Select({"select * from user_tab where user_id = #{user_id}"})
    @Results(id = "userdata", value = {@Result(property = "head_img", column = "head_img_id", one = @One(select = "dy.network.hundred.dao.ImageDao.findImageDDataByUserId", fetchType = FetchType.EAGER))})
    UserBean findUserDataByUserId(@PathParam("user_id") Integer paramInteger);

    @Select({"select * from user_tab where phone_num = #{phone_num}"})
    @ResultMap({"userdata"})
    UserBean findUserDataByUserPhoneNum(@PathParam("phone_num") String paramString);

    @Select({"select * from user_tab where province = #{province} and city = #{city} and district = #{district}"})
    @ResultMap({"userdata"})
    List<UserBean> findUserDataByAddress(UserBean paramUserBean);

    @Update({"update user_tab set child_a = #{child_a},child_b = #{child_b} where user_id = #{user_id}"})
    Integer updateUserDataRecommendAfter(UserBean paramUserBean);

    @Select({"select child_a,child_b from user_tab where user_id = #{userId}"})
    UserBean findUserChild(@PathParam("user_id") Integer paramInteger);

    @Select({"select count(user_id) from user_tab where user_id = #{user_id}"})
    Integer getUserExists(@PathParam("user_id") Integer paramInteger);

    @Select({"select phone_num from user_tab where user_id = #{user_id}"})
    String getUserPhoneNumByUid(@PathParam("user_id") Integer paramInteger);

    @Select({"select * from user_tab where recommend_user_phone = #{recommend_user_phone}"})
    @ResultMap({"userdata"})
    List<UserBean> findUserDataByUserRecommend_user_phone(@PathParam("recommend_user_phone") String paramString);

    @Update({"update user_tab set has_three = '1' where user_id = #{user_id}"})
    Integer openZoneThree(@PathParam("user_id") Integer paramInteger);

    @Select({"select integral_num,withdraw_num,payroll_num from user_tab where user_id = #{user_id}"})
    UserBean getUserProperty(@PathParam("user_id") Integer paramInteger);

    @Update({"update user_tab set integral_num = #{integral_num} where user_id = #{user_id}"})
    Integer updateUserIntegral_num(UserBean paramUserBean);

    @Update({"update user_tab set withdraw_num = #{withdraw_num} where user_id = #{user_id}"})
    Integer updateUserWithdraw_num(UserBean paramUserBean);

    @Update({"update user_tab set payroll_num = #{payroll_num} where user_id = #{user_id}"})
    Integer updateUserPayroll(UserBean paramUserBean);

    @Select({"select count(*) from user_tab where pay_password = #{pay_password} and user_id = #{user_id}"})
    Integer payPasswordIsTrue(UserBean paramUserBean);

    @Select({"select count(*) from user_tab where login_password = #{login_password} and user_id = #{user_id}"})
    Integer loginPasswordIsTrue(UserBean paramUserBean);

    @Select({"select has_three from user_tab where user_id = #{user_id}"})
    String WhetherToOpenZone_Three(@PathParam("user_id") Integer paramInteger);

    @Update({"update user_tab set child_a_num = #{child_a_num} where user_id = #{user_id}"})
    void updateUserChildANum(UserBean paramUserBean);

    @Update({"update user_tab set child_b_num = #{child_b_num} where user_id = #{user_id}"})
    void updateUserChildBNum(UserBean paramUserBean);


    @Select({"select * from user_tab where child_a = #{child_a}"})
    UserBean findUserDataByChildA(@PathParam("user_id") Integer paramInteger);

    @Select({"select * from user_tab where child_b = #{child_b}"})
    UserBean findUserDataByChildB(@PathParam("user_id") Integer paramInteger);


    @Update({"update user_tab set buy_integral = #{buy_integral} where user_id = #{user_id}"})
    void updateUserBuyIntegral(UserBean paramUserBean);

    /**
     * 更新登录时间
     *
     * @param userBean
     */
    @Update({"update user_tab set login_time = #{login_time} where user_id = #{user_id}"})
    void updateLoginTime(UserBean userBean);

    /**
     * 等级数
     *
     * @param normalUser
     * @return
     */
    @Select({"select count(user_id) from user_tab where grade = #{grade} "})
    int findRoleNum(String normalUser);

    @Select({"select count(user_id) from user_tab where" +
            " DATEDIFF(#{login_time}, login_time) <= 30 "})
    int thirtyDayUser(String login_time);

    @SelectProvider(type = UserDaoProvider.class, method = "Initializes")
    List<UserBean> getPageUserList(PageBean pageBean);

    @Update({"update user_tab set enable = #{enable} where user_id = #{user_id}"})
    int enableUser(UserBean userBean);

    @Update({"update user_tab set grade = #{grade} where user_id = #{user_id}"})
    int gradeUser(UserBean userBean);

    @Select({"select * from user_tab where  phone_num LIKE CONCAT('%',#{phone_num},'%')"})
    List<UserBean> findUserPhoneDim(String phone_num);

    @Select({"select * from user_tab where  name LIKE CONCAT('%',#{name},'%')"})
    List<UserBean> findUserListByNameDim(String name);
}
