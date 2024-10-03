package ir.maktabsharif115.springboot.service.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryUpdateDTO extends CategoryCreationDTO {

    @NotNull
    @Min(value = 1)
    private Long id;

    @NotNull
    private Boolean isActive;
}
