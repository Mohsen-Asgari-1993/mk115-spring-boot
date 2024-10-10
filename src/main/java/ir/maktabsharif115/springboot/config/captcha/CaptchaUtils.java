package ir.maktabsharif115.springboot.config.captcha;

import cn.apiclub.captcha.Captcha;
import ir.maktabsharif115.springboot.exceptions.GeneralRuntimeException;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.DatatypeConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Slf4j
public abstract class CaptchaUtils {

    public static final String CAPTCHA = "Captcha";

    public static String encodeBase64(Captcha captcha) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(captcha.getImage(), "png", outputStream);
            return DatatypeConverter.printBase64Binary(outputStream.toByteArray());
        } catch (IOException e) {
            log.error("error creating captcha: {}", e.getMessage());
        }
        return null;
    }

    public static void checkCaptcha(@NotNull String captcha, HttpSession httpSession, boolean shouldRemoveCaptcha) {
        String storedCaptcha = (String) httpSession.getAttribute(CAPTCHA);
        if (shouldRemoveCaptcha) {
            httpSession.removeAttribute(CAPTCHA);
        }
        if (storedCaptcha == null) {
            throw new GeneralRuntimeException("there is no captcha in server", HttpStatus.PRECONDITION_REQUIRED);
        }
        if (!captcha.equals(storedCaptcha)) {
            throw new GeneralRuntimeException("wrong captcha", HttpStatus.UNAUTHORIZED);
        }
    }
}
