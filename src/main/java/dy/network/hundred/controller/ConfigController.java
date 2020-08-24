package dy.network.hundred.controller;

import dy.network.hundred.java_bean.BaseBean;
import dy.network.hundred.java_bean.db_bean.ConfigBean;
import dy.network.hundred.java_bean.PageBean;
import dy.network.hundred.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ConfigController {

    @Autowired
    private ConfigService configService;

    @GetMapping("/getConfigList")
    public BaseBean<List<ConfigBean>> getConfigList(Integer page, Integer limit) {

        PageBean pageBean = new PageBean(page, limit, "", "");

        return configService.getConfigList(pageBean);
    }


    @PostMapping("/modify_config_info")
    public BaseBean modifyConfigInfo(@RequestBody ConfigBean configBean ) {

        return configService.modifyConfigInfo(configBean);
    }

}
