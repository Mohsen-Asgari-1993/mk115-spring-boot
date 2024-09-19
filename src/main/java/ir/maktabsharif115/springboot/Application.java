package ir.maktabsharif115.springboot;

import ir.maktabsharif115.springboot.service.MyService;
import jakarta.persistence.EntityManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Application.class, args);
        EntityManager entityManager = run.getBean(EntityManager.class);
        System.out.println("done");
    }

    @Bean
    public MyService myService() {
        return new MyService();
    }

}
