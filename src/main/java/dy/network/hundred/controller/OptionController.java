package dy.network.hundred.controller;

import dy.network.hundred.java_bean.db_bean.OptionBean;
import dy.network.hundred.service.OptionService;
import dy.network.hundred.java_bean.BaseBean;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OptionController {
  private static final Logger log = LoggerFactory.getLogger(OptionController.class);
  
  @Autowired
  private OptionService optionService;

  
  @PostMapping({"/public_option"})
  public BaseBean<Integer> userDeclareOneself(@RequestBody OptionBean optionBean) { return this.optionService.userDeclareOneself(optionBean); }


  
  @PostMapping({"/get_option_info"})
  public BaseBean<List<OptionBean>> findUserDeclareOneself() { return this.optionService.findUserDeclareOneself(); }
}
