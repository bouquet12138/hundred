package dy.network.hundred.task;


import dy.network.hundred.dao.ALGDao;
import dy.network.hundred.dao.PayrollDao;
import dy.network.hundred.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RegisterTest {
    private static final Logger log = LoggerFactory.getLogger(RegisterTest.class);


    @Autowired
    private UserDao userDao;

    @Autowired
    private PayrollDao payrollDao;

    @Autowired
    private ALGDao algDao;


    @Scheduled(cron = "0 10 0,1,2 * * ?")
    public void everyDayCalculate() {
        SendPayroll sendPayroll = new SendPayroll(userDao, payrollDao, algDao);
        sendPayroll.completeDelivery();
    }
}
