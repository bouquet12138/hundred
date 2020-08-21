package dy.network.hundred.service.impl;

import dy.network.hundred.dao.FlashDao;
import dy.network.hundred.java_bean.FlashBean;
import dy.network.hundred.java_bean.FlashContentBean;
import dy.network.hundred.service.FlashContentService;
import dy.network.hundred.service.FlashService;
import dy.network.hundred.java_bean.BaseBean;
import dy.network.hundred.utils.DateUtil;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service("flashService")
public class FlashServiceImpl implements FlashService {

    @Autowired
    private FlashDao flashDao;
    @Autowired
    private FlashContentService flashContentService;

    @Transactional(propagation = Propagation.REQUIRED)
    public BaseBean uploadFlash(FlashBean flashBean) {

        BaseBean baseBean = new BaseBean();
        flashBean.setPublic_time(DateUtil.getDate());
        flashBean.setReading_volume(0L);

        Integer flashId = flashDao.uploadFlash(flashBean);
        if (flashId > 0) {
            List<FlashContentBean> content_list = flashBean.getContent_list();
            int len = content_list.size();
            int size = 0;
            for (FlashContentBean flashContentBean : content_list) {
                flashContentBean.setFlash_id(flashBean.getFlash_id());
                flashContentService.uploadFlashContent(flashContentBean);
                size++;
            }
            if (len == size) {
                baseBean.setMsg("上传成功");
                baseBean.setCode(BaseBean.SUCCESS);
            } else {
                baseBean.setMsg("上传失败");

            }
        } else {
            baseBean.setMsg("上传失败");

        }
        return baseBean;
    }


    public BaseBean<List<FlashBean>> initFlash(FlashBean flashBean) {
        log.info("初始化快讯");
        BaseBean<List<FlashBean>> baseBean = new BaseBean();
        List<FlashBean> flashBeans = flashDao.initFlash(flashBean);
        if (flashBeans != null) {
            log.info(flashBeans.toString());
            baseBean.setCode(BaseBean.SUCCESS);
            baseBean.setMsg("初始化成功");
            baseBean.setData(flashBeans);
        } else {

            baseBean.setMsg("初始化失败");
        }
        return baseBean;
    }


    public BaseBean<List<FlashBean>> refreshFlash(FlashBean flashBean) {
        BaseBean<List<FlashBean>> baseBean = new BaseBean();
        List<FlashBean> flashBeans = this.flashDao.refreshFlash(flashBean);
        if (flashBeans != null) {
            if (flashBeans.size() != 0) {
                baseBean.setCode(BaseBean.SUCCESS);
                baseBean.setMsg("刷新成功");
                baseBean.setData(flashBeans);
            } else {
                baseBean.setCode(BaseBean.SUCCESS);
                baseBean.setMsg("没有更多数据");
            }
        } else {

            baseBean.setMsg("刷新失败");
        }
        return baseBean;
    }


    public BaseBean<List<FlashBean>> loadMoreFlash(FlashBean flashBean) {
        BaseBean<List<FlashBean>> baseBean = new BaseBean();
        List<FlashBean> flashBeans = this.flashDao.loadMoreFlash(flashBean);
        if (flashBeans != null) {
            if (flashBeans.size() != 0) {
                baseBean.setCode(BaseBean.SUCCESS);
                baseBean.setMsg("加载成功");
                baseBean.setData(flashBeans);
            } else {
                baseBean.setCode(BaseBean.SUCCESS);
                baseBean.setMsg("没有更多数据");
            }
        } else {

            baseBean.setMsg("加载失败");
        }
        return baseBean;
    }


    public BaseBean addFlashReadingVolume(int flash_id) {
        BaseBean baseBean = new BaseBean();
        Integer volume = this.flashDao.addFlashReadingVolume(flash_id);
        if (volume > 0) {
            baseBean.setCode(BaseBean.SUCCESS);
            baseBean.setMsg("增加成功");
        } else {

            baseBean.setMsg("增加失败");
        }
        return baseBean;
    }
}

