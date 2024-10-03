package ir.maktabsharif115.springboot.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = Role.TABLE_NAME)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Role implements Serializable {

    public static final String TABLE_NAME = "roles";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String ROLES_AUTHORITIES = "roles_authorities";
    public static final String ROLE_ID = "role_id";
    public static final String AUTHORITY_ID = "authority_id";

    @Id
    @GeneratedValue
    @Column(name = ID)
    private Long id;

    @Column(name = NAME)
    private String name;

    @ManyToMany
    @JoinTable(
            name = ROLES_AUTHORITIES,
            joinColumns = @JoinColumn(name = ROLE_ID),
            inverseJoinColumns = @JoinColumn(name = AUTHORITY_ID)
    )
    private Set<Authority> authorities = new HashSet<>();
}
