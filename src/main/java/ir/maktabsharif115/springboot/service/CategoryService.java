package ir.maktabsharif115.springboot.service;

import ir.maktabsharif115.springboot.domain.Category;
import ir.maktabsharif115.springboot.service.dto.CategoryCreationDTO;

@SuppressWarnings("unused")
public interface CategoryService {

    Category create(CategoryCreationDTO dto);
}
