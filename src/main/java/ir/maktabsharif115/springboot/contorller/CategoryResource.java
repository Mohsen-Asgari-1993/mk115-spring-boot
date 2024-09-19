package ir.maktabsharif115.springboot.contorller;

import ir.maktabsharif115.springboot.domain.Category;
import ir.maktabsharif115.springboot.service.CategoryService;
import ir.maktabsharif115.springboot.service.dto.CategoryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
//@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryResource {

    private final CategoryService categoryService;

    //    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @GetMapping(value = "/{id}")
    @ResponseBody
    public CategoryDTO findById(@PathVariable Long id) {
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
