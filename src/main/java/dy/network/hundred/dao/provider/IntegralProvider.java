package dy.network.hundred.dao.provider;

import dy.network.hundred.java_bean.PageBean;
import dy.network.hundred.utils.CollectionUtils;
import dy.network.hundred.utils.TextUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.jdbc.SQL;

@Slf4j
public class IntegralProvider {

    public String Initializes(PageBean pageBean) {


        String sql = new SQL() {
            {
                SELECT("*");
                FROM("integral_tab");

                if (!TextUtil.isEmpty(pageBean.getIntegral_type()))
                    WHERE("integral_type = #{integral_type}");

            }
        } + "";

        if (!CollectionUtils.isEmpty(pageBean.getUserIds())) {
            if (TextUtil.isEmpty(pageBean.getIntegral_type()))
                sql += " WHERE ";
            else
                sql += " and ";
            sql += "user_id in (";
            for (int i = 0; i < pageBean.getUserIds().size(); i++) {
                sql += pageBean.getUserIds().get(i);
                if (i != pageBean.getUserIds().size() - 1)
                    sql += ",";
            }
            sql += ")";
        }

        sql += "  ORDER BY integral_id  LIMIT " + ((pageBean.getPage() - 1) * pageBean.getLimit()) + "," + pageBean.getLimit();

        log.info(sql);

        return sql;
    }

}
