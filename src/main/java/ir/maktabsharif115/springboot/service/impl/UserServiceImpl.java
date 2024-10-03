package ir.maktabsharif115.springboot.service.impl;

import ir.maktabsharif115.springboot.domain.User;
import ir.maktabsharif115.springboot.exceptions.GeneralRuntimeException;
import ir.maktabsharif115.springboot.repository.UserRepository;
import ir.maktabsharif115.springboot.service.UserService;
import ir.maktabsharif115.springboot.service.dto.extra.UserRegisterDTO;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository baseRepository;

    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        if (baseRepository.count() == 0) {
            User user = new User();
            user.setUsername("mohsen");
            user.setPassword(passwordEncoder.encode("mohsen"));
            user.setFirstName("mohsen");
            user.setLastName("asgari");
            user.setIsActive(true);
            baseRepository.save(user);
        }
    }

    @Override
    @Transactional
    public void registerUser(UserRegisterDTO dto) {
        if (baseRepository.existsByUsername(dto.getUsername())) {
            throw new GeneralRuntimeException("duplicate username", HttpStatus.CONFLICT);
        }
        dto.setPassword(
                passwordEncoder.encode(dto.getPassword())
        );
        baseRepository.save(
                User.of(dto)
        );
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return baseRepository.findByUsername(username);
    }
}
