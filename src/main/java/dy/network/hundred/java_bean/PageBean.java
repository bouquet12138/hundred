package dy.network.hundred.java_bean;

import lombok.Data;

@Data
public class PageBean {

    private int page;
    private int limit;
    private String name;
    private String phone_num;

    public PageBean(int page, int limit, String name, String phone_num) {
        this.page = page;
        this.limit = limit;
        this.name = name;
        this.phone_num = phone_num;
    }
}
