package dy.network.hundred.java_bean;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ALGBean {

    private int num_a;
    private int num_b;

    private boolean isCumulative;
    private int add_money;


    public ALGBean(int num_a, int num_b, boolean isCumulative, int addMoney) {

        this.num_a = num_a;

        this.num_b = num_b;

        this.isCumulative = isCumulative;

        this.add_money = addMoney;
    }

    /**
     * 是否计算
     *
     * @param a
     * @param b
     * @return
     */
    public boolean WhetherTheCompound(int a, int b) {

        if (a >= this.num_a && b >= this.num_b)
            return true;

        return false;
    }


    public int getCumulativeDay(int a, int b, int c) {
        if (this.isCumulative) {
            if (a >= this.num_a && b >= this.num_b) {
                return a / this.num_a * 30;
            }
        }
        return 30;
    }
}


