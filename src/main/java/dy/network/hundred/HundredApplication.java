package dy.network.hundred;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@MapperScan({"dy.network.hundred.dao"})
@SpringBootApplication
public class HundredApplication {

    public static void main(String[] args) {
        SpringApplication.run(HundredApplication.class, args);
    }

}
