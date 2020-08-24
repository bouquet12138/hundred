package dy.network.hundred.service;



import dy.network.hundred.java_bean.db_bean.ImageBean;
import dy.network.hundred.java_bean.BaseBean;

public interface ImageService {
  BaseBean<ImageBean> uploadImage(ImageBean paramImageBean);
}
