package ir.maktabsharif115.springboot;

import ir.maktabsharif115.springboot.service.MyService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Application.class, args);

        System.out.println(
                run.getBean(MyService.class).getUsername()
        );

    }

    @Bean
    @Profile(value = {"dev"})
    public MyService myService() {
        return new MyService();
    }

}
