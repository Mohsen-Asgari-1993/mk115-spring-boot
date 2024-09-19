package ir.maktabsharif115.springboot;

import ir.maktabsharif115.springboot.repository.CategoryRepository;
import ir.maktabsharif115.springboot.service.dto.projection.IdProjection;
import ir.maktabsharif115.springboot.service.dto.projection.TitleProjection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Application.class, args);
        CategoryRepository categoryRepository = run.getBean(CategoryRepository.class);


        System.out.println(
                "IdProjection: " + categoryRepository.findById(1L, IdProjection.class).getId()
        );

        System.out.println(
                "TitleProjection: " + categoryRepository.findById(1L, TitleProjection.class).getTitle()
        );

        categoryRepository.findAllByIdIsNotNull(TitleProjection.class)
                .forEach(data -> System.out.println(data.getTitle()));

        categoryRepository.findAllByIdIsNotNull(IdProjection.class)
                .forEach(data -> System.out.println(data.getId()));

    }

}
