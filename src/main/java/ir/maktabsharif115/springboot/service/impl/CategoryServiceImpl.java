package ir.maktabsharif115.springboot.service.impl;

import ir.maktabsharif115.springboot.aspects.PrintMethodName;
import ir.maktabsharif115.springboot.domain.Category;
import ir.maktabsharif115.springboot.exceptions.GeneralRuntimeException;
import ir.maktabsharif115.springboot.repository.CategoryRepository;
import ir.maktabsharif115.springboot.service.CategoryService;
import ir.maktabsharif115.springboot.service.dto.CategoryCreationDTO;
import ir.maktabsharif115.springboot.util.CookieContextHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@SuppressWarnings("unused")
@Slf4j
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
    public void testAspect() {
        log.info("in testAspect method");
    }

    @Override
    @Cacheable(value = CACHE_NAME, key = "'all1'")
//    @Cacheable(value = CACHE_NAME)
    public List<Category> findAllForSite() {
        String cookie = CookieContextHolder.getCookie();
        return baseRepository.findAllByIsActive(true);
    }

    @Override
//    @CachePut(value = CACHE_NAME, /*key = "#category.id"*/ key = "#result.id")
//    @CacheEvict(value = CACHE_NAME, allEntries = true)
    @Caching(
            put = @CachePut(value = CACHE_NAME, /*key = "#category.id"*/ key = "#result.id"),
            evict = @CacheEvict(value = CACHE_NAME, key = "'all1'")
    )
    @Transactional
    public Category update(Category category) {
        Category needForUpdate = findById(category.getId());
        needForUpdate.setParent(category.getParent());
        needForUpdate.setTitle(category.getTitle());
        needForUpdate.setIsActive(category.getIsActive());
        return baseRepository.save(needForUpdate);
    }

    @Override
    @Cacheable(value = CACHE_NAME, key = "#id")
//    @Cacheable(value = CACHE_NAME, key = "T(java.util.Objects).hash(#id)")
    public Category findById(Long id) {
        return baseRepository.findById(id)
                .orElseThrow(
                        () -> new GeneralRuntimeException("category not found", HttpStatus.NOT_FOUND)
                );
    }

    @Override
    @Cacheable(value = CACHE_NAME, key = "'all2'")
//    @Cacheable(value = CACHE_NAME, key = "T(java.util.Objects).hash('all2')")
//    @Cacheable(value = CACHE_NAME)
    @PrintMethodName
    public List<Category> findAllForSiteTwo() {
        return baseRepository.findAllByIsActive(true);
    }

    @Override
    @PrintMethodName
    public void print(String text) {
        log.info("in print String");
    }

    @Override
    public void print(Long text) {
        log.info("in print Long");
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
