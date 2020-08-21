package dy.network.hundred.java_bean;

import lombok.Data;

@Data
public class BaseBean<T> {


    public static final int SUCCESS = 200;
    public static final int FAIL = -1;


    private int code = FAIL;//默认是失败
    private String msg;
    private T data;



}
