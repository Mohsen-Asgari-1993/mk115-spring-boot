package ir.maktabsharif115.springboot;

import ir.maktabsharif115.springboot.domain.Category;
import ir.maktabsharif115.springboot.service.CategoryService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Application.class, args);
        CategoryService categoryService = run.getBean(CategoryService.class);

        System.out.println(
                categoryService.findAll()
        );

        System.out.println(
                categoryService.findAll(Sort.by(Sort.Direction.DESC, "id"))
        );

        Page<Category> page = categoryService.findAll(
                PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "id"))
        );
        System.out.println("getTotalElements: " + page.getTotalElements());
        System.out.println("content: " + page.getContent());
    }

}
