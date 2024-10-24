package ir.maktabsharif115.springboot;

import ir.maktabsharif115.springboot.service.CategoryService;
import ir.maktabsharif115.springboot.service.SmsSender;
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
        categoryService.findAllForSiteTwo();
        categoryService.print("mohsen");
        categoryService.print(123456L);

        run.getBean(SmsSender.class).send("093711111111", "content");

    }

}
