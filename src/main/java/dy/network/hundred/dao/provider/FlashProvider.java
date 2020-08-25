package dy.network.hundred.dao.provider;

import dy.network.hundred.java_bean.PageBean;
import dy.network.hundred.utils.TextUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.jdbc.SQL;

@Slf4j
public class FlashProvider {

    public String Initializes(PageBean pageBean) {
        String sql = new SQL() {
            {
                SELECT("*");
                FROM("flash_tab");
            }
        } + "  ORDER BY flash_id  LIMIT " + ((pageBean.getPage() - 1) * pageBean.getLimit()) + "," + pageBean.getLimit();

        log.info(sql);

        return sql;
    }


}
