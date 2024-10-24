package ir.maktabsharif115.springboot.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class CategoryAspect {

    @Pointcut("execution(public void ir.maktabsharif115.springboot.service.impl.CategoryServiceImpl.testAspect())")
    public void logMethodName() {
    }

    @Before("logMethodName()")
    public void logBefore() {
        log.info("@Before testAspect");
    }

    @After("logMethodName()")
    public void logAfter() {
        log.info("@After testAspect");
    }

    @AfterReturning("logMethodName()")
    public void logAfterReturning() {
        log.info("@AfterReturning testAspect");
    }

    @AfterThrowing("logMethodName()")
    public void logAfterThrowing() {
        log.info("@AfterThrowing testAspect");
    }

}
