package ir.maktabsharif115.springboot.service.impl;

import ir.maktabsharif115.springboot.domain.Category;
import ir.maktabsharif115.springboot.repository.CategoryRepository;
import ir.maktabsharif115.springboot.service.CategoryService;
import ir.maktabsharif115.springboot.service.dto.CategoryCreationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@SuppressWarnings("unused")
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository baseRepository;

    @Override
    @Transactional
    public Category create(CategoryCreationDTO dto) {
        Category category = new Category();
        category.setTitle(dto.getTitle());
        setParent(category, dto.getParentId());
        return baseRepository.save(category);
    }

    @Override
    public List<Category> findAllForSite() {
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
