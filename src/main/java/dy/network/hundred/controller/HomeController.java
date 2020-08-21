package dy.network.hundred.controller;

import dy.network.hundred.java_bean.BaseBean;
import dy.network.hundred.java_bean.HomeBean;
import dy.network.hundred.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @Autowired
    private HomeService homeService;

    /**
     * 主页数据
     * @return
     */
    @PostMapping({"/home_data"})
    public BaseBean<HomeBean> homeData() {
        return homeService.homeData();
    }

}
