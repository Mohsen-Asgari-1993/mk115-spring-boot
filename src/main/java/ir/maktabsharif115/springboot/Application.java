package ir.maktabsharif115.springboot;

import ir.maktabsharif115.springboot.service.CategoryService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableCaching
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Application.class, args);
        CategoryService categoryService = run.getBean(CategoryService.class);
        categoryService.testAspect();
        categoryService.testAspect(8L);

    }

}
