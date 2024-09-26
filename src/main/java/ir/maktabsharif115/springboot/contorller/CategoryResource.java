package ir.maktabsharif115.springboot.contorller;

import ir.maktabsharif115.springboot.domain.Category;
import ir.maktabsharif115.springboot.service.CategoryService;
import ir.maktabsharif115.springboot.service.dto.CategoryCreationDTO;
import ir.maktabsharif115.springboot.service.dto.CategoryDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryResource {

    private final CategoryService categoryService;

    @PostMapping
    public CategoryDTO create(@RequestBody @Valid CategoryCreationDTO dto) {
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

    @PostMapping("/validation")
    public void validate(@RequestBody @Valid ValidationDTO dto) {

    }

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ValidationDTO implements Serializable {

        @NotNull
        private Long id;

        @Min(value = 50)
        private Long parentId;

        @Size(min = 2, max = 3)
        private List<String> names;

        @Valid
        private ValidationDetailDTO dto;

        @Valid
//        private List<@Valid ValidationDetailDTO> detailList;
        private List<ValidationDetailDTO> detailList;
    }

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ValidationDetailDTO implements Serializable {

        @NotNull
        private Long id;

        @Min(value = 10)
        @NotNull
        private Long parentId;

        @Size(min = 2, max = 3)
        private List<String> names;
    }
}
