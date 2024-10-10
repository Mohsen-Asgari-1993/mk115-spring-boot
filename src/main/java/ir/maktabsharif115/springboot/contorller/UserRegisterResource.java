package ir.maktabsharif115.springboot.contorller;

import cn.apiclub.captcha.Captcha;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import ir.maktabsharif115.springboot.config.captcha.CaptchaGenerator;
import ir.maktabsharif115.springboot.config.captcha.CaptchaTextProducer;
import ir.maktabsharif115.springboot.config.captcha.CaptchaUtils;
import ir.maktabsharif115.springboot.service.UserService;
import ir.maktabsharif115.springboot.service.dto.extra.UserRegisterDTO;
import ir.maktabsharif115.springboot.util.BasicResponse;
import ir.maktabsharif115.springboot.util.CookieContextHolder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.Semaphore;

@RestController
@RequestMapping("/user/register")
@RequiredArgsConstructor
public class UserRegisterResource {

    private final UserService userService;

    private final CaptchaGenerator captchaGenerator;

    private final Semaphore semaphore = new Semaphore(1, true);

    @PostMapping
    @Operation(
            description = "This is description",
            summary = "This is summary",
            responses = {
                    @ApiResponse(responseCode = "200"),
                    @ApiResponse(
                            responseCode = "400",
                            description = """
                                    possible messages:
                                        * one of required fields not valid
                                    """,
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            examples = {
                                                    @ExampleObject(
                                                            name = "required fields not filled",
                                                            value = """
                                                                    {
                                                                        "timestamp": "2023-02-20T13:04:41.598+00:00",
                                                                        "status": "BAD_REQUEST",
                                                                        "statusCode": 400,
                                                                        "errors": [
                                                                            {
                                                                                 "field": "firstName",
                                                                                 "message": "must not be blank"
                                                                            },
                                                                            {
                                                                                 "field": "lastName",
                                                                                 "message": "must not be blank"
                                                                            },
                                                                            {
                                                                                 "field": "mobileNumber",
                                                                                 "message": "must not be blank"
                                                                            },
                                                                            {
                                                                                 "field": "username",
                                                                                 "message": "must not be blank"
                                                                            },
                                                                            {
                                                                                 "field": "password",
                                                                                 "message": "must not be null"
                                                                            }
                                                                        ]
                                                                    }
                                                                    """
                                                    ),
                                                    @ExampleObject(
                                                            name = "another example",
                                                            value = """
                                                                    {
                                                                        "timestamp": "2023-02-20T13:04:41.598+00:00",
                                                                        "status": "BAD_REQUEST",
                                                                        "statusCode": 400,
                                                                        "errors": [
                                                                            {
                                                                                 "message": "another example"
                                                                            }
                                                                        ]
                                                                    }
                                                                    """
                                                    )
                                            }
                                    )
                            }
                    )
            }
    )
    @SneakyThrows
    public void registerUser(@RequestBody @Valid UserRegisterDTO dto, HttpSession httpSession) {

        CaptchaUtils.checkCaptcha(dto.getCaptcha(), httpSession, false);

        /*Object attribute = httpSession.getAttribute(CAPTCHA);
        if (attribute == null) {
            throw new GeneralRuntimeException(
                    "captcha in not set in server", HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
        if (!dto.getCaptcha().equals(attribute)) {
//            httpSession.removeAttribute(CAPTCHA);
            throw new GeneralRuntimeException("wrong captcha", HttpStatus.BAD_REQUEST);
        }*/
        semaphore.acquire();
        try {
            userService.registerUser(dto);
            httpSession.removeAttribute(CaptchaUtils.CAPTCHA);
        } finally {
            semaphore.release();
        }
    }

    @GetMapping("/captcha")
    public ResponseEntity<BasicResponse<String>> getCaptcha(HttpSession httpSession, HttpServletRequest request) {
//        HttpSession session = request.getSession(true);
        CaptchaTextProducer textProducer = new CaptchaTextProducer();
        System.out.println("captcha answer: " + textProducer.getAnswer());
        Captcha captcha = captchaGenerator.createCaptcha(
                150, 100, textProducer
        );
        httpSession.setAttribute(CaptchaUtils.CAPTCHA, textProducer.getAnswer());
        return ResponseEntity.ok(new BasicResponse<>("data:image/png;base64," + CaptchaUtils.encodeBase64(captcha)));
    }

    @GetMapping
    public void printCookie() {
        System.out.println(
                CookieContextHolder.getCookie()
        );
    }
}
