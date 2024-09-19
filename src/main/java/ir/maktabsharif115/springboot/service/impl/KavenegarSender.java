package ir.maktabsharif115.springboot.service.impl;

import ir.maktabsharif115.springboot.service.SmsSender;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
//@ConditionalOnProperty(value = "sms.kavenegar.status", havingValue = "active")
@ConditionalOnExpression(value = "${sms.kavenegar.status}")
public class KavenegarSender implements SmsSender {

    private final Config config;

    @Override
    public void send(String mobileNumber, String content) {
        System.out.println("in kavenegar");
        System.out.println("username: " + config.getUsername());
        System.out.println("password: " + config.getPassword());
        System.out.println("url: " + config.getUrl());
        System.out.println("token: " + config.getToken());
        System.out.println("callBackUrl: " + config.getCallBackUrl());
    }

    @Component
    @ConfigurationProperties(prefix = "sms.kavenegar")
//    @ConditionalOnProperty(value = "sms.kavenegar.status", havingValue = "active")
    @ConditionalOnExpression(value = "${sms.kavenegar.status}")
    @Getter
    @Setter
    public static class Config {

        private String status;

        private String username;

        private String password;

        private String url;

        private String token;

        private String callBackUrl;
    }

}
