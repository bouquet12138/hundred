package dy.network.hundred.utils;

import com.alibaba.fastjson.JSONObject;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;

@Controller
public class DataAnalysis
{
  public static List<List<String>> analysisString_toList(String data) {
    List<List<String>> stringList = new ArrayList<>();
    
    String[] arr1 = data.split("],");
    
    if (arr1 != null) {
      for (int i = 0; i < arr1.length; i++) {
        String str2 = arr1[i].replace("]", "");
        str2 = str2.replace("[", "");
        String[] arr2 = str2.split(",");
        if (arr2 != null) {
          List<String> strings = new ArrayList<>();
          for (int j = 0; j < arr2.length; j++) {
            strings.add(arr2[j]);
          }
          stringList.add(strings);
        } 
      } 
    }
    return stringList;
  }
  public static List<List<Integer>> analysisInteger_toList(String data) {
    List<List<Integer>> integerList = new ArrayList<>();
    
    String[] arr1 = data.split("],");
    
    if (arr1 != null) {
      for (int i = 0; i < arr1.length; i++) {
        String str2 = arr1[i].replace("]", "");
        str2 = str2.replace("[", "");
        
        String[] arr2 = str2.split(",");
        if (arr2 != null) {
          List<Integer> integers = new ArrayList<>();
          for (int j = 0; j < arr2.length; j++) {
            try {
              Integer integer = Integer.parseInt(arr2[j]);
              integers.add(integer);
            } catch (NumberFormatException numberFormatException) {}
          } 

          
          integerList.add(integers);
        } 
      } 
    }
    return integerList;
  }
  public static List<String> String_toList(String date) {
    String lString = "{\"name\" :" + date + "}";
    JSONObject parseObject = JSONObject.parseObject(lString);
    
    List<String> list = (List<String>)parseObject.get("name");
    return list;
  }
  public static List<Integer> Integer_toList(String date) {
    String lString = "{\"name\" :" + date + "}";
    JSONObject parseObject = JSONObject.parseObject(lString);
    
    List<Integer> list = (List<Integer>)parseObject.get("name");
    return list;
  }
  public static List<Integer> getImageList(String imgs) {
    List<Integer> imageList = new ArrayList<>();
    if (imgs.contains("[") && imgs.contains("]")) {
      imgs.replace("[", "");
      imgs.replace("]", "");
      String[] imgArr = imgs.split(",");
      if (imgArr != null) {
        for (int i = 0; i < imgArr.length; i++) {
          try {
            imageList.add(Integer.parseInt(imgArr[i]));
          } catch (NumberFormatException numberFormatException) {}
        } 
      }
    } 

    
    return imageList;
  }
}
