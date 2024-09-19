package ir.maktabsharif115.springboot.service;

import ir.maktabsharif115.springboot.domain.Category;
import ir.maktabsharif115.springboot.service.dto.extra.CategorySearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@SuppressWarnings("unused")
public interface CategoryService {

    Category save(Category category);

    Category findById(Long id);

    List<Category> findAll();

    List<Category> findAll(Sort sort);

    Page<Category> findAll(Pageable pageable);

    Page<Category> findAll(CategorySearch search, Pageable pageable);
}
