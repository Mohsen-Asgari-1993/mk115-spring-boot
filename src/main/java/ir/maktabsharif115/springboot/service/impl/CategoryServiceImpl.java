package ir.maktabsharif115.springboot.service.impl;

import ir.maktabsharif115.springboot.domain.Category;
import ir.maktabsharif115.springboot.repository.CategoryRepository;
import ir.maktabsharif115.springboot.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository baseRepository;

    @Override
    @Transactional
    public Category save(Category category) {
        return baseRepository.save(category);
    }


    @Override
    public Category findById(Long id) {
        return baseRepository.findById(id)
                .orElseThrow(
                        () -> new RuntimeException("entity not found")
                );
    }

    @Override
    public List<Category> findAll() {
        return baseRepository.findAll();
    }

    @Override
    public List<Category> findAll(Sort sort) {
        return baseRepository.findAll(sort);
    }

    @Override
    public Page<Category> findAll(Pageable pageable) {
        return baseRepository.findAll(pageable);
    }
}
