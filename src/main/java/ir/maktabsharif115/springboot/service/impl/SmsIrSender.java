package ir.maktabsharif115.springboot.service.impl;

import ir.maktabsharif115.springboot.service.SmsSender;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@ConditionalOnProperty(value = "sms.sms-ir.status", havingValue = "active")
public class SmsIrSender implements SmsSender {

    private final Config config;

    @Override
    public void send(String mobileNumber, String content) {
        System.out.println("in sms-ir");
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
    @ConditionalOnProperty(value = "sms.sms-ir.status", havingValue = "active")
    public static class Config {

        private String status;

        private String username;

        private String password;

        private String url;

        private String token;

        private String callBackUrl;
    }

}
