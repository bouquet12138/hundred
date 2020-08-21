package dy.network.hundred.controller;


import dy.network.hundred.java_bean.AppBean;
import dy.network.hundred.java_bean.App_Bean;
import dy.network.hundred.service.AppService;
import dy.network.hundred.utils.AppInfoUtil;
import dy.network.hundred.java_bean.BaseBean;
import dy.network.hundred.utils.FileUtil;
import dy.network.hundred.utils.DateUtil;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class AppController
{
  private static final Logger log = LoggerFactory.getLogger(AppController.class);

  
  @Autowired
  private AppService appService;

  
  @PostMapping({"/upload_app"})
  @Transactional(propagation = Propagation.REQUIRED)
  public BaseBean uploadAppData(MultipartFile app_icon, MultipartFile app_url, App_Bean app_bean) throws IOException {
    String app_icon_name = app_icon.getOriginalFilename();
    String app_url_name = app_url.getOriginalFilename();

    
    String app_icon_suffix = FileUtil.getSuffixName(app_icon_name);
    String app_url_suffix = FileUtil.getSuffixName(app_url_name);
    
    app_icon_name = "DY" + UUID.randomUUID() + app_icon_suffix;
    app_url_name = FileUtil.getPrefixName(app_url_name) + AppInfoUtil.getVersionName(app_url) + app_url_suffix;
    
    File app_icon_file = new File("C:/DY/app", app_icon_name);
    File app_url_file = new File("C:/DY/app", app_url_name);
    
    File dir = new File("C:/DY");
    if (!dir.exists())
      dir.mkdir(); 
    dir = new File("C:/DY/app");
    if (!dir.exists()) {
      dir.mkdir();
    }
    app_icon.transferTo(app_icon_file);
    app_url.transferTo(app_url_file);

    
    AppBean appBean = new AppBean();
    appBean.setPublic_date(DateUtil.getDate());
    
    long size = app_url.getSize();
    appBean.setApp_size(size);
    
    log.info(""+ app_bean.getUpdate_status());
    appBean.setUpdate_status(Integer.parseInt(app_bean.getUpdate_status()));
    appBean.setApp_md5(AppInfoUtil.calcMD5(app_url_file));
    appBean.setApp_name(app_url.getOriginalFilename());
    
    appBean.setApp_icon(app_icon_name);
    appBean.setApp_url(app_url_name);
    
    appBean.setApp_describe(app_bean.getApp_describe());

    
    appBean.setVersion_code(AppInfoUtil.getVersionCode(app_url_file));
    appBean.setVersion_name(AppInfoUtil.getVersionName(app_url_file));

    
    BaseBean baseBean = this.appService.addAppUpdateData(appBean);

    
    return baseBean;
  }


  
  @PostMapping({"/get_app_info"})
  public BaseBean<AppBean> findAppUpdateData() { return this.appService.findAppUpdateData(); }
}
