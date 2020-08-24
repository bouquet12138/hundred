package dy.network.hundred.service.impl;

import dy.network.hundred.dao.ImageDao;
import dy.network.hundred.dao.StoreDao;
import dy.network.hundred.java_bean.db_bean.ImageBean;
import dy.network.hundred.java_bean.db_bean.StoreBean;
import dy.network.hundred.service.StoreService;
import dy.network.hundred.java_bean.BaseBean;
import dy.network.hundred.utils.DataAnalysis;
import dy.network.hundred.utils.DateUtil;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("storeService")
public class StoreServiceImpl
        implements StoreService {
    @Autowired
    private StoreDao storeDao;
    @Autowired
    private ImageDao imageDao;

    public BaseBean addStoreInformation(StoreBean storeBean) {
        storeBean.setPublic_time(DateUtil.getDate());
        BaseBean baseBean = new BaseBean();
        Integer information = this.storeDao.addStoreInformation(storeBean);
        if (information > 0) {
            baseBean.setMsg("上传成功");
            baseBean.setCode(BaseBean.SUCCESS);
        } else {
            baseBean.setMsg("上传失败");

        }
        return baseBean;
    }


    public BaseBean<List<StoreBean>> InitializesTheStoreInformation(StoreBean storeBean) {
        BaseBean<List<StoreBean>> baseBean = new BaseBean();
        List<StoreBean> storeBeans = this.storeDao.InitializesTheStoreInformation(storeBean);
        if (storeBeans != null) {
            if (storeBeans.size() != 0) {
                for (StoreBean bean : storeBeans) {
                    if (bean.getProduct_img_ids() != null && !"".equals(bean.getProduct_img_ids())) {
                        List<Integer> list = DataAnalysis.Integer_toList(bean.getProduct_img_ids());
                        List<ImageBean> imageBeans = getImage(list);

                        bean.setProduct_imgs(imageBeans);
                    }
                }
            }

            baseBean.setData(storeBeans);
            baseBean.setCode(BaseBean.SUCCESS);
           baseBean.setMsg("初始化成功");
        } else {
            baseBean.setMsg("初始化失败");
        }

        return baseBean;
    }


    public BaseBean<List<StoreBean>> RefreshStoreInformation(StoreBean storeBean) {
        BaseBean<List<StoreBean>> baseBean = new BaseBean();
        List<StoreBean> storeBeanList = this.storeDao.RefreshStoreInformation(storeBean);
        if (storeBeanList != null) {
            if (storeBeanList.size() != 0) {
                for (StoreBean bean : storeBeanList) {
                    if (bean.getProduct_img_ids() != null && !"".equals(bean.getProduct_img_ids())) {
                        List<Integer> list = DataAnalysis.Integer_toList(bean.getProduct_img_ids());
                        List<ImageBean> imageBeans = getImage(list);

                        bean.setProduct_imgs(imageBeans);
                    }
                }


                baseBean.setData(storeBeanList);
                baseBean.setCode(BaseBean.SUCCESS);
                baseBean.setMsg("刷新成功");
            } else {
                baseBean.setCode(BaseBean.SUCCESS);
                baseBean.setMsg("没有更多数据");
            }
        } else {


            baseBean.setMsg("刷新失败");
        }
        return baseBean;
    }


    public BaseBean<List<StoreBean>> DropDownToLoadMoreProductInformation(StoreBean storeBean) {
        BaseBean<List<StoreBean>> baseBean = new BaseBean();
        List<StoreBean> storeBeans = this.storeDao.DropDownToLoadMoreProductInformation(storeBean);
        if (storeBeans != null) {
            if (storeBeans.size() != 0) {
                for (StoreBean bean : storeBeans) {
                    if (bean.getProduct_img_ids() != null && !"".equals(bean.getProduct_img_ids())) {
                        List<Integer> list = DataAnalysis.Integer_toList(bean.getProduct_img_ids());
                        List<ImageBean> imageBeans = getImage(list);

                        bean.setProduct_imgs(imageBeans);
                    }
                }

                baseBean.setData(storeBeans);
                baseBean.setCode(BaseBean.SUCCESS);
                baseBean.setMsg("加载成功");
            } else {
                baseBean.setCode(BaseBean.SUCCESS);
                baseBean.setMsg("没有更多数据");
            }
        } else {


            baseBean.setMsg("加载失败");
        }

        return baseBean;
    }

    private List<ImageBean> getImage(List<Integer> list) {
        List<ImageBean> imageBeans = new ArrayList<>();
        for (Integer integer : list) {
            ImageBean data = this.imageDao.findImageDDataByUserId(integer);
            imageBeans.add(data);
        }
        return imageBeans;
    }
 }
