package dy.network.hundred.controller;

import dy.network.hundred.java_bean.PageBean;
import dy.network.hundred.java_bean.db_bean.OptionBean;
import dy.network.hundred.java_bean.db_bean.UserBean;
import dy.network.hundred.service.OptionService;
import dy.network.hundred.java_bean.BaseBean;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class OptionController {

    @Autowired
    private OptionService optionService;


    @PostMapping({"/public_option"})
    public BaseBean<Integer> userDeclareOneself(@RequestBody OptionBean optionBean) {
        return this.optionService.userDeclareOneself(optionBean);
    }


    @PostMapping({"/get_option_info"})
    public BaseBean<List<OptionBean>> findUserDeclareOneself() {
        return this.optionService.findUserDeclareOneself();
    }

    @GetMapping("/getOptionList")
    public BaseBean<List<OptionBean>> getOptionList(Integer page, Integer limit) {

        PageBean pageBean = new PageBean(page, limit, "", "");

        return optionService.getOptionList(pageBean);
    }


}
