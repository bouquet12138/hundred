package dy.network.hundred.dao.provider;


import dy.network.hundred.java_bean.StoreBean;

import dy.network.hundred.utils.TextUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class StoreDaoProvider {

    public String Initializes(StoreBean storeBean) {
        String sql = new SQL() {
            {
                SELECT("*");
                FROM("store_tab");

                if (!TextUtil.isEmpty(storeBean.getStore_name()))
                    WHERE("store_name LIKE CONCAT('%',#{store_name},'%')");

                if (storeBean.getStore_id() > 0)
                    WHERE("store_id < #{store_id}");

                if (!TextUtil.isEmpty(storeBean.getProvince()))
                    WHERE("province LIKE CONCAT('%',#{province},'%')");
                if (!TextUtil.isEmpty(storeBean.getCity()))
                    WHERE("city LIKE CONCAT('%',#{city},'%')");

                if (!TextUtil.isEmpty(storeBean.getDistrict()))
                    WHERE("district LIKE CONCAT('%',#{district},'%')");
            }
        } + "  ORDER BY store_id DESC LIMIT 0,20";

        log.info(sql);

        return sql;
    }

    public String Refresh(StoreBean storeBean) {
        String sql = new SQL() {
            {
                SELECT("*");
                FROM("store_tab");

                if (!TextUtil.isEmpty(storeBean.getStore_name()))
                    WHERE("store_name LIKE CONCAT('%',#{store_name},'%')");

                if (storeBean.getStore_id() > 0)
                    WHERE("store_id > #{store_id}");

                if (!TextUtil.isEmpty(storeBean.getProvince()))
                    WHERE("province LIKE CONCAT('%',#{province},'%')");
                if (!TextUtil.isEmpty(storeBean.getCity()))
                    WHERE("city LIKE CONCAT('%',#{city},'%')");

                if (!TextUtil.isEmpty(storeBean.getDistrict()))
                    WHERE("district LIKE CONCAT('%',#{district},'%')");
            }
        } + "  ORDER BY store_id";

        log.info(sql);

        return sql;
    }

}
