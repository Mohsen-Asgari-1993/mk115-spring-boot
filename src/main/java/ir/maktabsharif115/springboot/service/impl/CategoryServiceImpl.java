package ir.maktabsharif115.springboot.service.impl;

import ir.maktabsharif115.springboot.domain.Category;
import ir.maktabsharif115.springboot.repository.CategoryRepository;
import ir.maktabsharif115.springboot.service.CategoryService;
import ir.maktabsharif115.springboot.service.dto.CategoryCreationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@SuppressWarnings("unused")
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository baseRepository;

    public static final String CACHE_NAME = "site_cats";

    @Override
    @Transactional
//    @CacheEvict(value = CACHE_NAME, key = "'all1'")
    @CacheEvict(value = CACHE_NAME, allEntries = true)
    public Category create(CategoryCreationDTO dto) {
        Category category = new Category();
        category.setTitle(dto.getTitle());
        setParent(category, dto.getParentId());
        return baseRepository.save(category);
    }

    @Override
    @Cacheable(value = CACHE_NAME, key = "'all1'")
//    @Cacheable(value = CACHE_NAME)
    public List<Category> findAllForSite() {
        return baseRepository.findAllByIsActive(true);
    }

    @Override
    @Cacheable(value = CACHE_NAME, key = "'all2'")
//    @Cacheable(value = CACHE_NAME)
    public List<Category> findAllForSiteTwo() {
        return baseRepository.findAllByIsActive(true);
    }

    private void setParent(Category category, Long parentId) {
        if (parentId != null) {
            category.setParent(
                    baseRepository.findById(parentId)
                            .orElseThrow(
                                    () -> new RuntimeException("wrong parentId")
                            )
            );
        } else {
            category.setParent(null);
        }
    }
}
