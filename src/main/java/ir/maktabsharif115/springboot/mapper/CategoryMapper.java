package ir.maktabsharif115.springboot.mapper;

import ir.maktabsharif115.springboot.domain.Category;
import ir.maktabsharif115.springboot.service.dto.CategoryCreationDTO;
import ir.maktabsharif115.springboot.service.dto.CategoryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CategoryMapper extends BaseMapper<Category, CategoryDTO> {

    @Mappings(
            value = {
                    @Mapping(target = "parentId", source = "parent.id")
            }
    )
//    @Mapping(target = "parentId", source = "parent.id")
    CategoryCreationDTO convertToDTO(Category category);
}
