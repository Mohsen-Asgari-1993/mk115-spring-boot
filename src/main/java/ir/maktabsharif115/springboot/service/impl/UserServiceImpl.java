package ir.maktabsharif115.springboot.service.impl;

import ir.maktabsharif115.springboot.domain.User;
import ir.maktabsharif115.springboot.exceptions.GeneralRuntimeException;
import ir.maktabsharif115.springboot.repository.UserRepository;
import ir.maktabsharif115.springboot.service.UserService;
import ir.maktabsharif115.springboot.service.dto.extra.UserRegisterDTO;
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

    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void registerUser(UserRegisterDTO dto) {
        if (repository.existsByUsername(dto.getUsername())) {
            throw new GeneralRuntimeException("duplicate username", HttpStatus.CONFLICT);
        }
        dto.setPassword(
                passwordEncoder.encode(dto.getPassword())
        );
        repository.save(
                User.of(dto)
        );
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return repository.findByUsername(username);
    }
}
