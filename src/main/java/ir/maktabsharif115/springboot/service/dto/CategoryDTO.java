package ir.maktabsharif115.springboot.service.dto;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDTO implements Serializable {

    private Long id;

    private String title;

    private CategoryDTO parent;
}
