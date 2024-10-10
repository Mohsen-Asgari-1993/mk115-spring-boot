package ir.maktabsharif115.springboot.service;

import ir.maktabsharif115.springboot.domain.Category;
import ir.maktabsharif115.springboot.service.dto.CategoryCreationDTO;

import java.util.List;

@SuppressWarnings("unused")
public interface CategoryService {

    Category create(CategoryCreationDTO dto);

    List<Category> findAllForSite();

    Category update(Category category);

    Category findById(Long id);

    List<Category> findAllForSiteTwo();
}
