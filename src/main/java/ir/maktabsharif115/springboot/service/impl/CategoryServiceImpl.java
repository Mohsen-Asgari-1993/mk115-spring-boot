package ir.maktabsharif115.springboot.service.impl;

import ir.maktabsharif115.springboot.domain.Category;
import ir.maktabsharif115.springboot.domain.Category_;
import ir.maktabsharif115.springboot.repository.CategoryRepository;
import ir.maktabsharif115.springboot.service.CategoryService;
import ir.maktabsharif115.springboot.service.dto.CategoryCreationDTO;
import ir.maktabsharif115.springboot.service.dto.extra.CategorySearch;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@SuppressWarnings("unused")
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository baseRepository;

    private final EntityManager entityManager;

    private final JdbcTemplate jdbcTemplate;

    private final JdbcClient jdbcClient;

    private final TestService testService;

    @Override
    @Transactional
    public Category create(CategoryCreationDTO dto) {
        test();
        testService.never();
        Category category = new Category();
        category.setTitle(dto.getTitle());
        return baseRepository.save(category);
    }

    @Override
    @Transactional(propagation = Propagation.NEVER)
    public void test() {
        System.out.println("in test method");
    }

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

    @Override
    public Page<Category> findAll(CategorySearch search, Pageable pageable) {
        return baseRepository.findAll(
                (root, query, cb) -> {
                    List<Predicate> predicates = new ArrayList<>();
                    fillTitlePredicate(predicates, root, cb, search.getTitle());
                    fillParentIdPredicate(predicates, root, cb, search.getParentId());
                    return predicates.isEmpty() ? null : cb.and(predicates.toArray(new Predicate[0]));
                },
                pageable
        );
    }

    private void fillTitlePredicate(List<Predicate> predicates, Root<Category> root, CriteriaBuilder cb, String title) {
        if (StringUtils.isNotBlank(title)) {
            predicates.add(
                    cb.like(
                            root.get(Category_.TITLE),
                            "%" + title + "%"
                    )
            );
        }
    }

    private void fillParentIdPredicate(List<Predicate> predicates, Root<Category> root, CriteriaBuilder cb, Long parentId) {
        if (parentId != null) {
            predicates.add(
                    cb.equal(
//                            root.get(Category_.PARENT).get(Category_.ID),
                            root.join(Category_.PARENT).get(Category_.ID),
                            parentId
                    )
            );
        }
    }
}
