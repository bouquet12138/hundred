package dy.network.hundred.dao.provider;

import dy.network.hundred.java_bean.GoodBean;
import dy.network.hundred.java_bean.OrderRecordBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GoodDaoProvider {
    private static final Logger log = LoggerFactory.getLogger(GoodDaoProvider.class);


    public String initializes(GoodBean goodBean) {
        String sql = "  ORDER BY good_id DESC LIMIT 0,20";

        log.info(sql);
        return sql;
    }


    public String init_order_record(OrderRecordBean orderRecordBean) {
        return "  ORDER BY order_id DESC LIMIT 0,20";
    }
}
