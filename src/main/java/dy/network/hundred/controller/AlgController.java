package dy.network.hundred.controller;

import dy.network.hundred.java_bean.ALGBean;
import dy.network.hundred.java_bean.BaseBean;
import dy.network.hundred.java_bean.PageBean;
import dy.network.hundred.service.AlgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AlgController {

    @Autowired
    private AlgService algService;

    @GetMapping("/getAlgList")
    public BaseBean<List<ALGBean>> getAlgList(Integer page, Integer limit) {

        PageBean pageBean = new PageBean(page, limit, "", "");

        return algService.getAlgList(pageBean);
    }


    @PostMapping("/modify_alg_info")
    public BaseBean modifyAlgInfo(@RequestBody ALGBean algBean ) {

        return algService.modifyAlgInfo(algBean);
    }

}
