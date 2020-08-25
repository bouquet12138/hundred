package dy.network.hundred.dao.provider;

import dy.network.hundred.java_bean.PageBean;
import dy.network.hundred.utils.CollectionUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WithdrawProvider {

    public String Initializes(PageBean pageBean) {
        String sql = "select * from withdraw_tab ";

        if (!CollectionUtils.isEmpty(pageBean.getUserIds())) {
            sql += " WHERE user_id in (";
            for (int i = 0; i < pageBean.getUserIds().size(); i++) {
                sql += pageBean.getUserIds().get(i);
                if (i != pageBean.getUserIds().size() - 1)
                    sql += ",";
            }
            sql += ")";
        }

        sql += "  ORDER BY withdraw_id  LIMIT " + ((pageBean.getPage() - 1) * pageBean.getLimit()) + "," + pageBean.getLimit();

        log.info(sql);

        return sql;
    }

}
