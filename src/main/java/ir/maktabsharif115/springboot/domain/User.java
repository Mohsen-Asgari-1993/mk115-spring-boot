package ir.maktabsharif115.springboot.domain;

import ir.maktabsharif115.springboot.service.dto.extra.UserRegisterDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = User.TABLE_NAME)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class User implements Serializable {

    public static final String TABLE_NAME = "users";

    public static final String ID = "id";
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String IS_ACTIVE = "is_active";
    public static final String CREATE_DATE = "create_date";
    public static final String LAST_UPDATE_DATE = "last_update_date";
    public static final String USERS_ROLES = "users_roles";
    public static final String USER_ID = "user_id";
    public static final String ROLE_ID = "role_id";

    @Id
    @GeneratedValue
    @Column(name = ID)
    private Long id;

    @Column(name = FIRST_NAME)
    private String firstName;

    @Column(name = LAST_NAME)
    private String lastName;

    @Column(name = USERNAME)
    private String username;

    @Column(name = PASSWORD)
    private String password;

    @Column(name = IS_ACTIVE)
    private Boolean isActive = false;

    @CreatedDate
//    @CreationTimestamp
    @Column(name = CREATE_DATE)
    private ZonedDateTime createDate;

    @LastModifiedDate
    @Column(name = LAST_UPDATE_DATE)
    private ZonedDateTime lastUpdateDate;

    @ManyToMany
    @JoinTable(
            name = USERS_ROLES,
            joinColumns = @JoinColumn(name = USER_ID),
            inverseJoinColumns = @JoinColumn(name = ROLE_ID)
    )
    private Set<Role> roles = new HashSet<>();

    public static User of(UserRegisterDTO dto) {
        User user = new User();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        return user;
    }

}
