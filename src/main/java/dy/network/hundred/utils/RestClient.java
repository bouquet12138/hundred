package dy.network.hundred.utils;

import java.util.Map;

import dy.network.hundred.java_bean.BaseBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;

/**
 * HTTP Rest Util
 *
 * @author yangzhilong
 */
public class RestClient {

    private static RestTemplate restTemplate;

    /**
     * @param client
     */
    public static void setRestTemplate(RestTemplate client) {
        restTemplate = client;
    }

    /**
     * @param <T>
     * @param url
     * @param clasz
     * @return
     */
    public static <T> T get(String url, Class<T> clasz) {
        return restTemplate.getForObject(url, clasz);
    }

    /**
     * RequestBody requestBody = new FormBody.Builder()
     * .add("deviceid", "")
     * .add("itemid", "")
     * .add("wayNum", "")
     * .build();
     * <p>
     * Request request = new Request.Builder()
     * .url("https://api.huaruanzc.net/backend-api/deviceManagerController.do?deviceStart")
     * .post(requestBody)
     * .addHeader("Content-Type", "multipart/form-data")
     * .addHeader("Authorization", "eyJhbGciOiJIUzUxMiIsInppcCI6IkRFRiJ9.eNqqVsosLlayUiovL9fLKE0sKk2sStZLzs9V0lHKTCxRsjI0tbQwMDOxMDbQUSouTQKqNLQwNjMxsTS2NDRWqgUAAAD__w.2QFC6Q34juX1AwK0UP25zXFbrQLgQ7otCQLockg-yunjuo2Zq4hxANvSltJSn2Cx7tEz115FsZyB6KUxMMp7fw")
     * .build();
     * <p>
     * Response response = client.newCall(request).execute();
     *
     * @param url
     * @param clasz
     * @param <T>
     * @return
     */
    public static <T> T deviceStart(String url, String authorization, String deviceid, String itemid, Class<T> clasz) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Authorization", authorization);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("deviceid", deviceid);
        params.add("itemid", itemid);
        params.add("wayNum", "");

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);

        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, clasz).getBody();
    }

    /**
     * @param <T>
     * @param url
     * @param attrMap
     * @param clasz
     * @return
     */
    public static <T> T postForm(String url, Map<String, String> attrMap, Class<T> clasz) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        attrMap.entrySet().forEach(item -> {
            params.add(item.getKey(), item.getValue());
        });
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);

        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, clasz).getBody();
    }


}