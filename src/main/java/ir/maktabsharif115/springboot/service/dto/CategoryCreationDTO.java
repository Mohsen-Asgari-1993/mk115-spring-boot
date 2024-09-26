package ir.maktabsharif115.springboot.service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryCreationDTO implements Serializable {

    @NotBlank
//    @NotNull
//    @Min(value = 5)
//    @Max(value = 100)
//    @Pattern(regexp = "\\w")
//    @Email
//    @Size(min = 5)
    private String title;

    private Long parentId;
}