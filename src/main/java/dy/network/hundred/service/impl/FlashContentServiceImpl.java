package dy.network.hundred.service.impl;

import dy.network.hundred.dao.FlashContentDao;
import dy.network.hundred.java_bean.FlashContentBean;
import dy.network.hundred.service.FlashContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("flashContentService")
public class FlashContentServiceImpl
        implements FlashContentService {
    @Autowired
    private FlashContentDao flashContentDao;

    public Integer uploadFlashContent(FlashContentBean flashContentBean) {
        Integer flashContent = this.flashContentDao.uploadFlashContent(flashContentBean);
        return flashContent;
    }
 }