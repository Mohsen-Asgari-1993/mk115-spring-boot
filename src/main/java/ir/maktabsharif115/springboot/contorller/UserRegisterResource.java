package ir.maktabsharif115.springboot.contorller;

import ir.maktabsharif115.springboot.service.UserService;
import ir.maktabsharif115.springboot.service.dto.extra.UserRegisterDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Semaphore;

@RestController
@RequestMapping("/user/register")
@RequiredArgsConstructor
public class UserRegisterResource {

    private final UserService userService;

    private final Semaphore semaphore = new Semaphore(1, true);

    @PostMapping
    @SneakyThrows
    public void registerUser(@RequestBody @Valid UserRegisterDTO dto) {
        semaphore.acquire();
        try {
            userService.registerUser(dto);
        } finally {
            semaphore.release();
        }
    }
}
