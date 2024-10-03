package ir.maktabsharif115.springboot.contorller;

import ir.maktabsharif115.springboot.domain.Category;
import ir.maktabsharif115.springboot.mapper.CategoryMapper;
import ir.maktabsharif115.springboot.service.CategoryService;
import ir.maktabsharif115.springboot.service.dto.CategoryCreationDTO;
import ir.maktabsharif115.springboot.service.dto.CategoryDTO;
import ir.maktabsharif115.springboot.service.dto.CategoryUpdateDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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
    }


    @PutMapping
    public ResponseEntity<CategoryDTO> update(@RequestBody @Valid CategoryUpdateDTO dto) {
        return ResponseEntity.ok(
                categoryMapper.convertEntityToDTO(
                        categoryService.update(
                                categoryMapper.toDomain(dto)
                        )
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> findById(@PathVariable Long id, Authentication authentication) {
        System.out.println(authentication.getName());
        Authentication staticAuth = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(
                categoryMapper.convertEntityToDTO(
                        categoryService.findById(id)
                )
        );
    }
}
