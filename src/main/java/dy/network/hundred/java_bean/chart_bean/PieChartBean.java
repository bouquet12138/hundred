package dy.network.hundred.java_bean.chart_bean;

import lombok.Data;

@Data
public class PieChartBean {

    private int value;

    private String name;

    public PieChartBean(int value, String name) {
        this.value = value;
        this.name = name;
    }
}
