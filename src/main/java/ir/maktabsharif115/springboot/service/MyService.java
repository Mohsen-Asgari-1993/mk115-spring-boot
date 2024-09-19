package ir.maktabsharif115.springboot.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

@Getter
@Setter
public class MyService {

    @Value("${my-username}")
    private String username;
}
