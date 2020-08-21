package dy.network.hundred.utils;

import java.util.ArrayList;
import java.util.List;


public class TextUtil
{
  public static boolean isEmpty(String str) { return (str == null || str.equals("")); }

  
  public static List<Integer> getImageList(String str) {
    List<Integer> imageList = new ArrayList<>();
    
    if (isEmpty(str)) {
      return imageList;
    }
    if (str.contains("[") && str.contains("]")) {
      
      str = str.replace("[", "");
      str = str.replace("]", "");
      
      String[] strArr = str.split(",");
      for (String _str : strArr) {
        Integer i = Integer.parseInt(_str);
        imageList.add(i);
      } 
    } 
    
    return imageList;
  }
}
