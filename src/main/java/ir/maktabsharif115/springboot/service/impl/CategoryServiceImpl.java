package ir.maktabsharif115.springboot.service.impl;

import ir.maktabsharif115.springboot.domain.Category;
import ir.maktabsharif115.springboot.repository.CategoryRepository;
import ir.maktabsharif115.springboot.service.CategoryService;
import ir.maktabsharif115.springboot.service.dto.CategoryCreationDTO;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@SuppressWarnings("unused")
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository baseRepository;

    @Override
    @Transactional
    public Category create(CategoryCreationDTO dto) {
        if (StringUtils.isBlank(dto.getTitle())) {
            throw new RuntimeException("title is blank");
        }
        Category category = new Category();
        category.setTitle(dto.getTitle());
        setParent(category, dto.getParentId());
        return baseRepository.save(category);
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
