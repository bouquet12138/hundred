package dy.network.hundred.service.impl;

import dy.network.hundred.dao.ImageDao;
import dy.network.hundred.java_bean.ImageBean;
import dy.network.hundred.service.ImageService;
import dy.network.hundred.java_bean.BaseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("imageService")
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageDao imageDao;

    public BaseBean<ImageBean> uploadImage(ImageBean imageBean) {
        BaseBean<ImageBean> baseBean = new BaseBean();
        Integer headImage = this.imageDao.uploadImage(imageBean);
        if (headImage > 0) {
            baseBean.setCode(BaseBean.SUCCESS);
            baseBean.setMsg("上传成功");
            baseBean.setData(imageBean);
        } else {

            baseBean.setMsg("上传失败");
        }
        return baseBean;
    }
 }
