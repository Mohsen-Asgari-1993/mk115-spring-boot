package ir.maktabsharif115.springboot.contorller;

import ir.maktabsharif115.springboot.domain.Category;
import ir.maktabsharif115.springboot.service.CategoryService;
import ir.maktabsharif115.springboot.service.dto.CategoryCreationDTO;
import ir.maktabsharif115.springboot.service.dto.CategoryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryResource {

    private final CategoryService categoryService;

    @PostMapping
    public CategoryDTO create(@RequestBody CategoryCreationDTO dto) {
        Category category = categoryService.create(dto);
        return new CategoryDTO(
                category.getId(),
                category.getTitle(),
                category.getParent() != null ?
                        CategoryDTO.builder()
                                .id(category.getId())
                                .title(category.getTitle())
                                .parent(
                                        category.getParent() != null ?
                                                CategoryDTO.builder()
                                                        .id(category.getParent().getId())
                                                        .title(category.getParent().getTitle())
                                                        .build() : null
                                ).build()
                        : null
        );
    }
}
