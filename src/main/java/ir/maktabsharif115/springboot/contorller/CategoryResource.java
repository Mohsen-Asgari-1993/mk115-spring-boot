package ir.maktabsharif115.springboot.contorller;

import ir.maktabsharif115.springboot.config.CustomUserDetails;
import ir.maktabsharif115.springboot.domain.Category;
import ir.maktabsharif115.springboot.mapper.CategoryMapper;
import ir.maktabsharif115.springboot.service.CategoryService;
import ir.maktabsharif115.springboot.service.dto.CategoryCreationDTO;
import ir.maktabsharif115.springboot.service.dto.CategoryDTO;
import ir.maktabsharif115.springboot.service.dto.CategoryUpdateDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@Slf4j
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
    @PreAuthorize("hasAuthority(T(ir.maktabsharif115.springboot.util.AuthorityNames).CATEGORY_READ)")
//    @PreAuthorize("hasAuthority('category-manage') || hasAuthority('category-manage-2')")
//    @PreAuthorize("hasAnyAuthority('category-manage', 'category-manage-2')")
//    @PreAuthorize("hasRole('ADMIN')")/*ROLE_ADMIN*/
//    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")/*ROLE_ADMIN*/
    public ResponseEntity<CategoryDTO> findById(@PathVariable Long id, Authentication authentication) {
        log.info(authentication.getName());
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication securityContextAuthentication = securityContext.getAuthentication();
        log.info(securityContextAuthentication.getName());

        if (securityContextAuthentication.getPrincipal() instanceof CustomUserDetails myUser) {
            log.info("userId : " + myUser.getUser().getId());
        }

        return ResponseEntity.ok(
                categoryMapper.convertEntityToDTO(
                        categoryService.findById(id)
                )
        );
    }

    @GetMapping("/admin")
    public void adminRole() {

    }
}
