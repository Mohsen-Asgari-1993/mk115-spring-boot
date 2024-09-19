package ir.maktabsharif115.springboot.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "sms.sms-ir")
@Getter
@Setter
public class SmsIrConfig {

    private String username;

    private String password;

    private String url;

    private String token;

    private String callBackUrl;
}
