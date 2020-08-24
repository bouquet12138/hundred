package dy.network.hundred.java_bean.db_bean;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DemachineBean {

    private int demachine_id;
    private String sn;
    private String deviceid;
    private String devicename;
    private String itemid;

    @Override
    public String toString() {
        return "DemachineBean{" +
                "demachine_id=" + demachine_id +
                ", sn='" + sn + '\'' +
                ", deviceid='" + deviceid + '\'' +
                ", devicename='" + devicename + '\'' +
                ", itemid='" + itemid + '\'' +
                '}';
    }
}
