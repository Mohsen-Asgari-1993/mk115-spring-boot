package ir.maktabsharif115.springboot.service;

import ir.maktabsharif115.springboot.domain.Category;
import ir.maktabsharif115.springboot.service.dto.CategoryCreationDTO;
import ir.maktabsharif115.springboot.service.impl.CategoryServiceImpl;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

@SuppressWarnings("unused")
public interface CategoryService {

    Category create(CategoryCreationDTO dto);

    List<Category> findAllForSite();

    Category update(Category category);

    Category findById(Long id);

    @Cacheable(value = CategoryServiceImpl.CACHE_NAME, key = "'all2'")
//    @Cacheable(value = CACHE_NAME)
    List<Category> findAllForSiteTwo();
}
