package ir.maktabsharif115.springboot.repository;

import ir.maktabsharif115.springboot.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
