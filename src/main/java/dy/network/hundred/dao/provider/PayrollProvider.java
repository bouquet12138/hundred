package dy.network.hundred.dao.provider;

import dy.network.hundred.java_bean.PageBean;
import dy.network.hundred.utils.CollectionUtils;
import dy.network.hundred.utils.TextUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.jdbc.SQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PayrollProvider {

    public String Initializes(PageBean pageBean) {


        String sql = new SQL() {
            {
                SELECT("*");
                FROM("payroll_tab");

                if (!TextUtil.isEmpty(pageBean.getIntegral_type()))
                    WHERE("type = #{integral_type}");

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

        sql += "  ORDER BY payroll_id  LIMIT " + ((pageBean.getPage() - 1) * pageBean.getLimit()) + "," + pageBean.getLimit();

        log.info(sql);

        return sql;
    }


}
