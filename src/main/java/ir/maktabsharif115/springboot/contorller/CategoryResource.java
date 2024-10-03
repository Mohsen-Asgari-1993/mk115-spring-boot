package ir.maktabsharif115.springboot.contorller;

import ir.maktabsharif115.springboot.domain.Category;
import ir.maktabsharif115.springboot.exceptions.GeneralRuntimeException;
import ir.maktabsharif115.springboot.mapper.CategoryMapper;
import ir.maktabsharif115.springboot.service.CategoryService;
import ir.maktabsharif115.springboot.service.dto.CategoryCreationDTO;
import ir.maktabsharif115.springboot.service.dto.CategoryDTO;
import ir.maktabsharif115.springboot.validators.AllowedValues;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryResource {

    private final CategoryService categoryService;

    private final CategoryMapper categoryMapper;

    @PostMapping
    public ResponseEntity<CategoryDTO> create(@RequestBody @Valid CategoryCreationDTO dto) {
        Category category = categoryService.create(dto);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("my-custom-header", "my-custom-header-value");
        return new ResponseEntity<>(
                categoryMapper.convertEntityToDTO(category),
                httpHeaders,
                HttpStatus.CREATED
        );
        /*return new ResponseEntity<>(
                new CategoryDTO(
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
                ),
                httpHeaders,
                HttpStatus.CREATED
        );*/
    }

    @PostMapping("/validation")
    public void validate(@RequestBody @Valid ValidationDTO dto) {

    }

    @GetMapping("/exception/run-time")
    public void getRuntimeException() {
        throw new RuntimeException("this is message");
    }

    @GetMapping("/exception/custom")
    public void getCustomException() {
        throw new GeneralRuntimeException("this is custom message", HttpStatus.PRECONDITION_FAILED);
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

        @AllowedValues(required = false, values = {"1", "2"}, message = "only '1', '2' is allowed")
        private String type;

        @AllowedValues(values = {"1", "2"}, message = "only '1', '2' is allowed")
        private String model;

        @AllowedValues(values = {"RED", "BLUE"}, message = "only 'RED', 'BLUE' is allowed")
        private List<String> colors;

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
