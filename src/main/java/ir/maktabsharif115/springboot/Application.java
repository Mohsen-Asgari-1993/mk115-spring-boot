package ir.maktabsharif115.springboot;

import ir.maktabsharif115.springboot.service.SmsSender;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Application.class, args);
        run.getBean(SmsSender.class).send(null, null);
    }

}
