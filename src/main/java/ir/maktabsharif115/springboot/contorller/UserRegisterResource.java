package ir.maktabsharif115.springboot.contorller;

import ir.maktabsharif115.springboot.exceptions.GeneralRuntimeException;
import ir.maktabsharif115.springboot.service.UserService;
import ir.maktabsharif115.springboot.service.dto.extra.UserRegisterDTO;
import ir.maktabsharif115.springboot.util.BasicResponse;
import ir.maktabsharif115.springboot.util.CookieContextHolder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.Semaphore;

@RestController
@RequestMapping("/user/register")
@RequiredArgsConstructor
public class UserRegisterResource {

    public static final String CAPTCHA = "CAPTCHA";

    private final UserService userService;

    private final Semaphore semaphore = new Semaphore(1, true);

    @PostMapping
    @SneakyThrows
    public void registerUser(@RequestBody @Valid UserRegisterDTO dto, HttpSession httpSession) {
        Object attribute = httpSession.getAttribute(CAPTCHA);
        if (attribute == null) {
            throw new GeneralRuntimeException(
                    "captcha in not set in server", HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
        if (!dto.getCaptcha().equals(attribute)) {
//            httpSession.removeAttribute(CAPTCHA);
            throw new GeneralRuntimeException("wrong captcha", HttpStatus.BAD_REQUEST);
        }
        semaphore.acquire();
        try {
            userService.registerUser(dto);
            httpSession.removeAttribute(CAPTCHA);
        } finally {
            semaphore.release();
        }
    }

    @GetMapping("/captcha")
    public ResponseEntity<BasicResponse<String>> getCaptcha(HttpSession httpSession, HttpServletRequest request) {
//        HttpSession session = request.getSession(true);
        String captcha = RandomStringUtils.randomNumeric(5);
        httpSession.setAttribute(CAPTCHA, captcha);
        return ResponseEntity.ok(
                new BasicResponse<>(captcha)
        );
    }

    @GetMapping
    public void printCookie() {
        System.out.println(
                CookieContextHolder.getCookie()
        );
    }
}
