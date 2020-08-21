package dy.network.hundred.dao.provider;

import dy.network.hundred.java_bean.PageBean;
import dy.network.hundred.utils.TextUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.jdbc.SQL;

@Slf4j
public class UserDaoProvider {

    public String Initializes(PageBean pageBean) {
        String sql = new SQL() {
            {
                SELECT("*");
                FROM("user_tab");

                if (!TextUtil.isEmpty(pageBean.getName()))
                    WHERE("name LIKE CONCAT('%',#{name},'%')");


                if (!TextUtil.isEmpty(pageBean.getPhone_num()))
                    WHERE("phone_num LIKE CONCAT('%',#{phone_num},'%')");

            }
        } + "  ORDER BY user_id  LIMIT " + ((pageBean.getPage() - 1) * pageBean.getLimit()) + "," + pageBean.getLimit();

        log.info(sql);

        return sql;
    }

}
