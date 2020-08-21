package dy.network.hundred.utils;

import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BackupUtil {
  private static final Logger log = LoggerFactory.getLogger(BackupUtil.class);


  
  private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
  
 /* public static void backUp() {
    String dbName = "wisdomdb";
    String username = "root";
    String password = "root";
    File uploadDir = new File("C:/");
    
    if (!uploadDir.exists()) {
      uploadDir.mkdirs();
    }
    
    String cmd = "mysqldump -u" + username + " -p" + password + " " + dbName + " -r " + "C:/+ "/" + dbName + "" + simpleDateFormat.format(new Date()) + ".sql";
    log.info("cmd:" + cmd);
    try {
      Process process = Runtime.getRuntime().exec(cmd);
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }*/

}
