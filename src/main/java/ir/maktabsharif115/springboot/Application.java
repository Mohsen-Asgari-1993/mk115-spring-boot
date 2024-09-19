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

        System.out.println(
                "findAllByTitleJpql: " + categoryRepository.findAllByTitleJpql("sp")
        );
        System.out.println(
                "findAllByTitleNative: " + categoryRepository.findAllByTitleNative("sp")
        );
        System.out.println(
                "findAllByTitleContainingIgnoreCase: " + categoryRepository.findAllByTitleContainingIgnoreCase("sp")
        );

    }

}
