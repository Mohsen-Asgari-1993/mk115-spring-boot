package ir.maktabsharif115.springboot.contorller;

import ir.maktabsharif115.springboot.domain.Category;
import ir.maktabsharif115.springboot.service.CategoryService;
import ir.maktabsharif115.springboot.service.dto.CategoryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryResource {

    private final CategoryService categoryService;


    //    @GetMapping("/{id}/{name}")
//    /categories/1/myname
    @GetMapping
//    /categories?id=1&name=myname&key=value
    public CategoryDTO findById(@RequestParam Long id, @RequestParam(required = false) String name) {
        Category category = categoryService.findById(id);
        return new CategoryDTO(
                category.getId(), category.getTitle(),
                category.getParent() != null ? new CategoryDTO(
                        category.getParent().getId(),
                        category.getParent().getTitle(),
                        null
                ) : null
        );
    }
}
