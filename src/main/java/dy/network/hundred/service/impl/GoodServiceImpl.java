package dy.network.hundred.service.impl;

import dy.network.hundred.dao.GoodDao;
import dy.network.hundred.dao.ImageDao;
import dy.network.hundred.dao.PayrollDao;
import dy.network.hundred.dao.UserDao;
import dy.network.hundred.java_bean.*;
import dy.network.hundred.java_bean.db_bean.*;
import dy.network.hundred.service.GoodService;
import dy.network.hundred.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("goodService")
public class GoodServiceImpl implements GoodService {
    private static final Logger log = LoggerFactory.getLogger(GoodServiceImpl.class);

    @Autowired
    private GoodDao goodDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ImageDao imageDao;

    @Autowired
    private PayrollDao payrollDao;

    public BaseBean<Integer> uploadGood(GoodBean goodBean) {
        BaseBean<Integer> baseBean = new BaseBean();
        goodBean.setInsert_time(DateUtil.getDate());

        Integer result = this.goodDao.addGoodInformation(goodBean);
        if (result > 0) {
            baseBean.setCode(BaseBean.SUCCESS);
            baseBean.setMsg("上传成功");
            baseBean.setData(goodBean.getGood_id());
        } else {
            baseBean.setMsg("上传失败");
        }

        return baseBean;
    }


    public BaseBean modifyGood(GoodBean goodBean) {
        BaseBean baseBean = new BaseBean();
        Integer result = this.goodDao.modifyGood(goodBean);
        if (result == null || result <= 0) {
            baseBean.setMsg("修改失败");
        } else {
            baseBean.setMsg("修改成功");
            baseBean.setCode(BaseBean.SUCCESS);
        }
        return baseBean;
    }


    public BaseBean deleteGood(GoodBean goodBean) {
        BaseBean baseBean = new BaseBean();
        Integer result = this.goodDao.deleteGood(goodBean);
        if (result == null || result <= 0) {
            baseBean.setMsg("删除失败");
        } else {
            baseBean.setMsg("删除成功");
            baseBean.setCode(BaseBean.SUCCESS);
        }
        return baseBean;
    }


    public BaseBean<List<GoodBean>> initGoodList(GoodBean goodBean) {
        BaseBean<List<GoodBean>> baseBean = new BaseBean();


        List<GoodBean> goodBeanList = this.goodDao.initGoodList(goodBean);
        setGoodImageList(goodBeanList);

        baseBean.setMsg("初始化成功");
        baseBean.setData(goodBeanList);
        baseBean.setCode(BaseBean.SUCCESS);


        return baseBean;
    }


    public BaseBean<List<GoodBean>> loadMoreGoodList(GoodBean goodBean) {
        BaseBean<List<GoodBean>> baseBean = new BaseBean();


        List<GoodBean> goodBeanList = this.goodDao.initGoodList(goodBean);
        setGoodImageList(goodBeanList);
        baseBean.setMsg("加载成功");
        baseBean.setData(goodBeanList);
        baseBean.setCode(BaseBean.SUCCESS);


        return baseBean;
    }


    private void setGoodImageList(List<GoodBean> goodBeanList) {
        for (GoodBean _goodBean : goodBeanList) {
            setGoodImage(_goodBean);
        }
    }

    private void setGoodImage(GoodBean _goodBean) {
        if (_goodBean == null)
            return;
        List<Integer> imageList = TextUtil.getImageList(_goodBean.getImage_ids());
        List<ImageBean> imageBeanList = new ArrayList<>();

        for (Integer img_id : imageList) {
            ImageBean imageBean = this.imageDao.findImageDDataByUserId(img_id);
            if (imageBean != null) {
                imageBeanList.add(imageBean);
            }
        }
        _goodBean.setImage_list(imageBeanList);
    }


    @Transactional
    public BaseBean buyGood(OrderRecordBean orderRecordBean) {
        BaseBean baseBean = new BaseBean();

        GoodBean goodBean = this.goodDao.findGoodById(orderRecordBean.getGood_id());

        if (goodBean == null) {
            baseBean.setMsg("商品不存在");
            return baseBean;
        }

        if ("0".equals(goodBean.getStatus())) {
            baseBean.setMsg("商品已下架");
            return baseBean;
        }

        UserBean userBean = this.userDao.findUserDataByUserId(orderRecordBean.getUser_id());

        if (userBean == null) {
            baseBean.setMsg("用户不存在");
            return baseBean;
        }

        userBean.setPay_password(MD5Utils.generateMD5(orderRecordBean.getPay_pass()));

        int pay_true = this.userDao.payPasswordIsTrue(userBean);

        if (pay_true <= 0) {
            baseBean.setMsg("支付密码错误");
            return baseBean;
        }

        int money = orderRecordBean.getGood_count() * goodBean.getPrice();
        log.info("商品价格" + money);

        String serial = DateUtil.getOrderIdByTime(userBean.getUser_id());

        OrderRecordBean _orderBean = this.goodDao.findOrderWithSerial(serial);

        while (_orderBean != null) {
            serial = DateUtil.getOrderIdByTime(userBean.getUser_id());
            _orderBean = this.goodDao.findOrderWithSerial(serial);
        }

        orderRecordBean.setStatus("待发货");
        orderRecordBean.setSerial(serial);
        orderRecordBean.setInsert_time(DateUtil.getDate());
        orderRecordBean.setGood_type(goodBean.getGood_type());

        if ("积分".equals(orderRecordBean.getBuy_type())) {
            if (userBean.getBuy_integral() < money) {
                baseBean.setMsg("购物积分不足");
                return baseBean;
            }
            int result = BuyIntegerUtil.addBuyIntegerRecord(this.userDao, userBean.getUser_id(), -money);
            if (result > 0) {
                this.goodDao.addBuyGoodRecord(orderRecordBean);
                baseBean.setCode(BaseBean.SUCCESS);
                baseBean.setMsg("购买成功");
                return baseBean;
            }
        } else if ("工资".equals(orderRecordBean.getBuy_type())) {
            if (userBean.getPayroll_num() < money) {
                baseBean.setMsg("工资不足");
                return baseBean;
            }
            boolean result = PayrollUtil.addPayrollRecord(PayrollBean.OTHER, -money, userBean.getUser_id(), null, "", 0, "购物", this.userDao, this.payrollDao);

            if (result) {
                this.goodDao.addBuyGoodRecord(orderRecordBean);
                baseBean.setCode(BaseBean.SUCCESS);
                baseBean.setMsg("购买成功");
                return baseBean;
            }
        }

        baseBean.setMsg("购买失败");

        return baseBean;
    }


    public BaseBean<List<OrderRecordBean>> initOrderRecord(OrderRecordBean orderRecordBean) {
        BaseBean<List<OrderRecordBean>> baseBean = new BaseBean();
        List<OrderRecordBean> orderRecordBeans = this.goodDao.initOrderRecord(orderRecordBean);

        for (OrderRecordBean _orderRecordBean : orderRecordBeans) {
            setGoodImage(_orderRecordBean.getGood_bean());
        }

        baseBean.setData(orderRecordBeans);
        baseBean.setMsg("初始化成功");
        baseBean.setCode(BaseBean.SUCCESS);

        return baseBean;
    }


    public BaseBean<List<OrderRecordBean>> loadMoreOrderRecord(OrderRecordBean orderRecordBean) {
        BaseBean<List<OrderRecordBean>> baseBean = new BaseBean();
        List<OrderRecordBean> orderRecordBeans = this.goodDao.initOrderRecord(orderRecordBean);

        for (OrderRecordBean _orderRecordBean : orderRecordBeans) {
            setGoodImage(_orderRecordBean.getGood_bean());
        }

        baseBean.setData(orderRecordBeans);

        if (orderRecordBeans == null || orderRecordBeans.size() == 0) {
            baseBean.setMsg("没有更多数据");
        } else {
            baseBean.setMsg("加载成功");
        }
        baseBean.setCode(BaseBean.SUCCESS);

        return baseBean;
    }


    public BaseBean<DeliverNumBean> getDeliverGoodNum(UserBean userBean) {
        BaseBean<DeliverNumBean> baseBean = new BaseBean();

        OrderRecordBean orderRecordBean = new OrderRecordBean();
        orderRecordBean.setUser_id(userBean.getUser_id());
        orderRecordBean.setStatus("待发货");

        int wait_delivery = this.goodDao.getDelivery(orderRecordBean);
        orderRecordBean.setStatus("已发货");
        int had_delivery = this.goodDao.getDelivery(orderRecordBean);
        DeliverNumBean deliverNumBean = new DeliverNumBean(wait_delivery, had_delivery);
        baseBean.setData(deliverNumBean);
        baseBean.setCode(BaseBean.SUCCESS);
        baseBean.setMsg("获取成功");

        return baseBean;
    }
}

