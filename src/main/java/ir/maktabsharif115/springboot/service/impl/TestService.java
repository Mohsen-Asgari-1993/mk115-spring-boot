package ir.maktabsharif115.springboot.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TestService {

    @Transactional(propagation = Propagation.NEVER)
    public void never() {

    }
}
