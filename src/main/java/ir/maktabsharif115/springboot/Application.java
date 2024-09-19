package ir.maktabsharif115.springboot;

import ir.maktabsharif115.springboot.domain.Category;
import ir.maktabsharif115.springboot.service.CategoryService;
import ir.maktabsharif115.springboot.service.dto.extra.CategorySearch;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Application.class, args);
        CategoryService categoryService = run.getBean(CategoryService.class);

        Page<Category> page = categoryService.findAll(
                CategorySearch.builder()
                        .title("s")
                        .build(),
                PageRequest.of(0, 10)
        );
        if (page.hasContent()) {
            System.out.println(page.getContent().getFirst());
        } else {
            System.out.println("empty");
        }

    }

}
