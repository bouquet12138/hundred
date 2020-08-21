package dy.network.hundred.java_bean;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DeMachineNumBean {

    private int demachine_num_id;
    private int demachine_num;
    private int user_id;
    private String insert_time;
    private String update_time;

    public DeMachineNumBean(int demachine_num, int user_id, String insert_time) {
        this.demachine_num = demachine_num;
        this.user_id = user_id;
        this.insert_time = insert_time;
    }
}
