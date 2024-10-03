package ir.maktabsharif115.springboot.repository;

import ir.maktabsharif115.springboot.domain.Category;
import ir.maktabsharif115.springboot.service.dto.projection.CategoryBriefProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@SuppressWarnings("unused")
public interface CategoryRepository extends JpaRepository<Category, Long>,
        JpaSpecificationExecutor<Category>, CategoryCustomRepository {

    @Override
    @EntityGraph(value = Category.FULL_GRAPH)
//    @EntityGraph(attributePaths = Category_.PARENT)
    List<Category> findAll();

    @EntityGraph(value = Category.FULL_GRAPH)
//    @EntityGraph(attributePaths = Category_.PARENT)
    List<Category> findAllByIsActive(Boolean isActive);

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


    @Query(
            nativeQuery = true,
            value = "select c.id as id, c.title as title from category c where c.id = :id"
    )
    CategoryBriefProjection findByIdProjection(@Param("id") Long id);

    <P> P findById(Long id, Class<P> pClass);

    <P> List<P> findAllByIdIsNotNull(Class<P> pClass);

    /*@Query(
            """
                    select c.id as id, c.title as title,
                    (select count(p.id) from posts p where p.categoryId = c.id) as usage
                    from Category c
                    """
    )
    Page<CategoryUsageProjection> findAllByUsage(Pageable pageable);*/
}

@SuppressWarnings("unused")
interface CategoryCustomRepository {

    List<Category> findAllByJdbcTemplate();

    List<Category> findAllByJdbcClient();

}

@RequiredArgsConstructor
class CategoryCustomRepositoryImpl implements CategoryCustomRepository {

    private final JdbcClient jdbcClient;

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Category> findAllByJdbcTemplate() {
        return jdbcTemplate.query(
                "select * from category",
                (rs, rowNum) -> {
                    Category category = new Category();
                    category.setId(rs.getLong(1));
                    category.setTitle(rs.getString(2));
                    return category;
                }
        );
    }

    @Override
    public List<Category> findAllByJdbcClient() {
        return jdbcClient.sql("select * from category")
                .query(
                        (rs, rowNum) -> {
                            Category category = new Category();
                            category.setId(rs.getLong(1));
                            category.setTitle(rs.getString(2));
                            return category;
                        }
                ).list();
    }
}
