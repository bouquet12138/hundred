package dy.network.hundred.java_bean.chart_bean;

import lombok.Data;

import java.util.List;

@Data
public class LineChartBean {

    private int index = 0;

    private String name;

    private String type = "line";

    private List<Integer> data;


    public LineChartBean(int index, String name, String type, List<Integer> data) {
        this.index = index;
        this.name = name;
        this.type = type;
        this.data = data;
    }
}
