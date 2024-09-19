package ir.maktabsharif115.springboot.service.dto.extra;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategorySearch implements Serializable {

    private String title;

    private Long parentId;
}
