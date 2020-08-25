package dy.network.hundred.dao.provider;

import dy.network.hundred.java_bean.PageBean;
import dy.network.hundred.utils.TextUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.jdbc.SQL;

@Slf4j
public class OptionProvider {

    public String Initializes(PageBean pageBean) {
        String sql = new SQL() {
            {
                SELECT("*");
                FROM("option_tab");
            }
        } + "  ORDER BY option_id  LIMIT " + ((pageBean.getPage() - 1) * pageBean.getLimit()) + "," + pageBean.getLimit();

        log.info(sql);

        return sql;
    }

}
