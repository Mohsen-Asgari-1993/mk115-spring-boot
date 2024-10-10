package ir.maktabsharif115.springboot.config.captcha;

import cn.apiclub.captcha.backgrounds.BackgroundProducer;
import cn.apiclub.captcha.backgrounds.GradiatedBackgroundProducer;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.image.BufferedImage;


@Component
public class DefaultCaptchaBackgroundProducer implements BackgroundProducer {

    private final BackgroundProducer backgroundProducer = new GradiatedBackgroundProducer(Color.LIGHT_GRAY, Color.GRAY);

    @Override
    public BufferedImage addBackground(BufferedImage image) {
        return backgroundProducer.addBackground(image);
    }

    @Override
    public BufferedImage getBackground(int width, int height) {
        return backgroundProducer.getBackground(width, height);
    }
}
