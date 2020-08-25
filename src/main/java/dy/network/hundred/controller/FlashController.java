package dy.network.hundred.controller;


import dy.network.hundred.java_bean.PageBean;
import dy.network.hundred.java_bean.db_bean.FlashBean;
import dy.network.hundred.service.FlashService;
import dy.network.hundred.java_bean.BaseBean;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class FlashController {

    @Autowired
    private FlashService flashService;


    @PostMapping({"/upload_flash"})
    public BaseBean uploadFlash(@RequestBody FlashBean flashBean) {
        return flashService.uploadFlash(flashBean);
    }


    @PostMapping({"/init_flash"})
    public BaseBean<List<FlashBean>> initFlash(@RequestBody FlashBean flashBean) {
        log.info("初始化");
        return flashService.initFlash(flashBean);
    }


    @PostMapping({"/refresh_flash"})
    public BaseBean<List<FlashBean>> refreshFlash(@RequestBody FlashBean flashBean) {
        return flashService.refreshFlash(flashBean);
    }


    @PostMapping({"/load_more_flash"})
    public BaseBean<List<FlashBean>> loadMoreFlash(@RequestBody FlashBean flashBean) {
        return flashService.loadMoreFlash(flashBean);
    }


    @PostMapping({"/add_flash_reading_volume"})
    public BaseBean addFlashReadingVolume(@RequestBody FlashBean flashBean) {
        return flashService.addFlashReadingVolume(flashBean.getFlash_id());
    }



    @PostMapping({"/delete_flash"})
    public BaseBean deleteFlash(@RequestBody FlashBean flashBean) {
        return flashService.deleteFlash(flashBean.getFlash_id());
    }


    @GetMapping({"/getFlashList"})
    public BaseBean<List<FlashBean>> getFlashList(Integer page, Integer limit) {
        PageBean pageBean = new PageBean(page, limit, "", "");
        return flashService.getFlashList(pageBean);
    }

}
