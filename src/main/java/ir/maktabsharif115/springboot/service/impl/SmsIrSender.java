package ir.maktabsharif115.springboot.service.impl;

import ir.maktabsharif115.springboot.service.SmsSender;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SmsIrSender implements SmsSender {

    private final Config config;

    @Override
    public void send(String mobileNumber, String content) {
        System.out.println("username: " + config.getUsername());
        System.out.println("password: " + config.getPassword());
        System.out.println("url: " + config.getUrl());
        System.out.println("token: " + config.getToken());
        System.out.println("callBackUrl: " + config.getCallBackUrl());
    }

    @Component
    @ConfigurationProperties(prefix = "sms.sms-ir")
    @Getter
    @Setter
    public static class Config {

        private String username;

        private String password;

        private String url;

        private String token;

        private String callBackUrl;
    }

}
