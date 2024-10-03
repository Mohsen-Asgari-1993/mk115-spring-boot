package ir.maktabsharif115.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.ZonedDateTime;
import java.util.Optional;

@Configuration
@EnableJpaAuditing(dateTimeProviderRef = "DateTimeProvider")
public class SecurityConfig {

    @Bean("DateTimeProvider") // Makes ZonedDateTime compatible with auditing fields
    @Primary
    public DateTimeProvider auditingDateTimeProvider() {
        return () -> Optional.of(ZonedDateTime.now());
    }

    /*@Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService,
                                                         PasswordEncoder passwordEncoder) {
        return new AuthenticationProvider() {

            @Override
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                UserDetails userDetails = userDetailsService.loadUserByUsername(authentication.getName());
                if (!userDetails.isEnabled()) {
                    throw new DisabledException("user is not enabled");
                }
                if (passwordEncoder.matches(((String) authentication.getCredentials()), userDetails.getPassword())) {
                    return new UsernamePasswordAuthenticationToken(
                            authentication.getPrincipal(),
                            authentication.getCredentials(),
                            new ArrayList<>()
                    );
                }
                throw new BadCredentialsException("bad credential");
            }

            @Override
            public boolean supports(Class<?> authentication) {
                return true;
            }
        };
    }*/

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
