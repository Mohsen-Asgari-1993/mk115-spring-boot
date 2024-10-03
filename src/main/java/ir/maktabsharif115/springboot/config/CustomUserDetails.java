package ir.maktabsharif115.springboot.config;

import ir.maktabsharif115.springboot.domain.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@Getter
public class CustomUserDetails implements UserDetails {

    private final User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(user.getRoles())) {
            user.getRoles().forEach(
                    role -> {
                        authorities.add(new SimpleGrantedAuthority(role.getName()));
                        if (CollectionUtils.isNotEmpty(role.getAuthorities())) {
                            role.getAuthorities().forEach(
                                    authority -> authorities.add(new SimpleGrantedAuthority(authority.getName()))
                            );
                        }
                    }
            );
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isEnabled() {
        return user.getIsActive();
    }
}
