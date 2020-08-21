package dy.network.hundred.contract;

import java.io.File;

public class FileContract {

  public static final String ROOT_DIR = "C:/HD";

  
  public static final String APP_DIR = "C:/HD/app";
  
  public static final String IMG_DIR = "C:/HD/img";

  /**
   * 创建文件夹
   */
  public static void makeDir(){
    File dir = new File(ROOT_DIR);
    if (!dir.exists())
      dir.mkdir();
    dir = new File(APP_DIR);
    if (!dir.exists()) {
      dir.mkdir();
    }
    dir = new File(IMG_DIR);//图片文件夹不存在就创建
    if (!dir.exists()) {
      dir.mkdir();
    }
  }

}
