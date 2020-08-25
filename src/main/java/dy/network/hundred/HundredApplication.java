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
    }

}
