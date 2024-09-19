package ir.maktabsharif115.springboot;

import ir.maktabsharif115.springboot.repository.CategoryRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Application.class, args);
        CategoryRepository categoryRepository = run.getBean(CategoryRepository.class);

        categoryRepository.findProjectionCustom()
                .forEach(projection -> System.out.println("id: " + projection.getId() + " title: " + projection.getTitle()));

    }

}
