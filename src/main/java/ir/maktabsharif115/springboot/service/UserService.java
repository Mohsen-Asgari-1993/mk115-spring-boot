package ir.maktabsharif115.springboot.service;

import ir.maktabsharif115.springboot.domain.User;
import ir.maktabsharif115.springboot.service.dto.extra.UserRegisterDTO;

import java.util.Optional;

public interface UserService {

    void registerUser(UserRegisterDTO dto);

    Optional<User> findByUsername(String username);
}
