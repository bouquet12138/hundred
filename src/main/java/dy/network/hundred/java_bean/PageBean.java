package dy.network.hundred.java_bean;

import lombok.Data;

import java.util.List;

@Data
public class PageBean {

    private int page;
    private int limit;

    private String name;
    private String phone_num;

    private String integral_type;

    private List<Integer> userIds;//对应的用户id

    public PageBean(int page, int limit, String name, String phone_num) {
        this.page = page;
        this.limit = limit;
        this.name = name;
        this.phone_num = phone_num;
    }
}
