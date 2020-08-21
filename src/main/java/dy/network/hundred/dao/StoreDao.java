package dy.network.hundred.dao;


import dy.network.hundred.java_bean.StoreBean;
import dy.network.hundred.dao.provider.StoreDaoProvider;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

@Repository("storeDao")
public interface StoreDao {
    @Insert({"insert into store_tab(store_name,head_img_id,store_type,province,city,district,detailed_address,longitude,latitude,business_hours,store_describe,contact_phone,product_img_ids,public_time) values(#{store_name},#{head_img_id},#{store_type},#{province},#{city},#{district},#{detailed_address},#{longitude},#{latitude},#{business_hours},#{store_describe},#{contact_phone},#{product_img_ids},#{public_time})"})
    Integer addStoreInformation(StoreBean paramStoreBean);

    @SelectProvider(type = StoreDaoProvider.class, method = "Initializes")
    @Results(id = "store_data", value = {@Result(property = "head_img", column = "head_img_id", one = @One(select = "dy.network.hundred.dao.ImageDao.findImageDDataByUserId", fetchType = FetchType.EAGER))})
    List<StoreBean> InitializesTheStoreInformation(StoreBean paramStoreBean);

    @SelectProvider(type = StoreDaoProvider.class, method = "Refresh")
    @ResultMap({"store_data"})
    List<StoreBean> RefreshStoreInformation(StoreBean paramStoreBean);

    @SelectProvider(type = StoreDaoProvider.class, method = "Initializes")
    @ResultMap({"store_data"})
    List<StoreBean> DropDownToLoadMoreProductInformation(StoreBean paramStoreBean);
}
