package ir.maktabsharif115.springboot.service;

public interface SmsSender {

    void send(String mobileNumber, String content);
}
