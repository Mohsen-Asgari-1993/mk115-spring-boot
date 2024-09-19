package ir.maktabsharif115.springboot.service.impl;

import ir.maktabsharif115.springboot.config.SmsIrConfig;
import ir.maktabsharif115.springboot.service.SmsSender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SmsIrSender implements SmsSender {

    private final SmsIrConfig config;

    @Override
    public void send(String mobileNumber, String content) {
        System.out.println("username: " + config.getUsername());
        System.out.println("password: " + config.getPassword());
        System.out.println("url: " + config.getUrl());
        System.out.println("token: " + config.getToken());
        System.out.println("callBackUrl: " + config.getCallBackUrl());
    }

}
