package ir.maktabsharif115.springboot.mapper;

import ir.maktabsharif115.springboot.domain.Category;
import ir.maktabsharif115.springboot.service.dto.CategoryCreationDTO;
import ir.maktabsharif115.springboot.service.dto.CategoryDTO;
import ir.maktabsharif115.springboot.service.dto.CategoryUpdateDTO;
import ir.maktabsharif115.springboot.service.dto.extra.CategorySiteDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper extends BaseMapper<Category, CategoryDTO> {

    @Mappings(
            value = {
                    @Mapping(target = "parentId", source = "parent.id")
            }
    )
//    @Mapping(target = "parentId", source = "parent.id")
    CategoryCreationDTO convertToDTO(Category category);

    List<CategorySiteDTO> convertToSiteDTO(List<Category> categories);

    Category toDomain(CategoryUpdateDTO dto);
}
