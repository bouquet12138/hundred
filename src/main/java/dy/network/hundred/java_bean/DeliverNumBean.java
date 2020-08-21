package dy.network.hundred.java_bean;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DeliverNumBean {
    private int wait_delivery;
    private int had_delivery;


    public DeliverNumBean(int wait_delivery, int had_delivery) {
        this.wait_delivery = wait_delivery;
        this.had_delivery = had_delivery;
    }
}


