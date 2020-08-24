package dy.network.hundred.service;


import dy.network.hundred.java_bean.db_bean.FlashBean;
import dy.network.hundred.java_bean.BaseBean;

import java.util.List;

public interface FlashService {
    BaseBean uploadFlash(FlashBean paramFlashBean);

    BaseBean<List<FlashBean>> initFlash(FlashBean flashBean);

    BaseBean<List<FlashBean>> refreshFlash(FlashBean flashBean);

    BaseBean<List<FlashBean>> loadMoreFlash(FlashBean flashBean);

    BaseBean addFlashReadingVolume(int paramInt);
}
