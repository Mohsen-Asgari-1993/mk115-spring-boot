package ir.maktabsharif115.springboot.domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = Category.TABLE_NAME)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@NamedEntityGraph(
        name = Category.FULL_GRAPH,
        attributeNodes = @NamedAttributeNode(value = Category_.PARENT)
)
public class Category implements Serializable {

    public static final String TABLE_NAME = "category";
    public static final String FULL_GRAPH = "category_full_graph";
    public static final String TITLE = "title";
    public static final String PARENT_ID = "parent_id";

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = TITLE)
    private String title;

    @ManyToOne
    @JoinColumn(name = PARENT_ID)
    private Category parent;
}
