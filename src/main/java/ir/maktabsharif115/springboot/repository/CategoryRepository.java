package ir.maktabsharif115.springboot.repository;

import ir.maktabsharif115.springboot.domain.Category;
import ir.maktabsharif115.springboot.service.dto.projection.CategoryBriefProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    //    JPQL
    @Query(
            "select c from Category c where c.title like concat('%', :title, '%') "
    )
    List<Category> findAllByTitleJpql(@Param("title") String title);


    //    Native
    @Query(
            nativeQuery = true,
            value = "select * from category as c where c.title like concat('%', ?1, '%')"
    )
    List<Category> findAllByTitleNative(String title);

    List<Category> findAllByTitleContainingIgnoreCase(String title);

    List<Category> findAllByParent_IdIsNull();

    List<Category> findAllByParent_Id(Long parentId);

    @Query(
            nativeQuery = true,
            value = "select c.id as id, c.title as title from category c"
    )
    List<CategoryBriefProjection> findProjectionCustom();

}
