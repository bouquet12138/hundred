package dy.network.hundred;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@EnableScheduling
@MapperScan({"dy.network.hundred.dao"})
@SpringBootApplication
public class HundredApplication {

    public static void main(String[] args) {
        SpringApplication.run(HundredApplication.class, args);

        log.info("-----------------------------------------------------------------------");
        log.info("       *\t\t\t\t\t\t\t好好工作");
        log.info("       **\t\t\t\t\t\t\t好好生活");
        log.info("*       ***");
        log.info("**       ****");
        log.info("******************\t\t\t\t\t祝小朋友节日快乐");
        log.info("**       ****");
        log.info("*       ***");
        log.info("       **\t\t\t\t\t\t\t祝你爱的家人永远陪在你的身边");
        log.info("       *\t\t\t\t\t\t\t希望兄弟情可以维持的时间长一点");
        log.info("-----------------------------------------------------------------------");

    }

}
