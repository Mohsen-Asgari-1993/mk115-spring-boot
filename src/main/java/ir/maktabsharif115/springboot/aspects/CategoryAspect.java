package ir.maktabsharif115.springboot.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Aspect
@Slf4j
public class CategoryAspect {

    //    @Pointcut("@within(ir.maktabsharif115.springboot.service.CategoryService)")
    @Pointcut("""
            execution(public * ir.maktabsharif115.springboot.service.CategoryService.*(*)) ||
            execution(public * ir.maktabsharif115.springboot.service.CategoryService.*())
            """)
    public void logMethodName() {
    }

    @Before("logMethodName()")
    public void logBefore(JoinPoint joinPoint) {
        log.info("@Before {}", joinPoint.getSignature().getName());
    }

    @After("logMethodName()")
    public void logAfter(JoinPoint joinPoint) {
        log.info("@After {}", joinPoint.getSignature().getName());
    }

    @AfterReturning("logMethodName()")
    public void logAfterReturning(JoinPoint joinPoint) {
        log.info("@AfterReturning {}", joinPoint.getSignature().getName());
    }

    @AfterThrowing("logMethodName()")
    public void logAfterThrowing(JoinPoint joinPoint) {
        log.info("@AfterThrowing {}", joinPoint.getSignature().getName());
    }

    @Pointcut("execution(public void ir.maktabsharif115.springboot.service.CategoryService.print(Long))")
    public void printPointCut() {
    }

    @Before("printPointCut()")
    public void beforePrint(JoinPoint joinPoint) {
        log.info("@Before {}", joinPoint.getSignature().getName());
    }

    @Before("@annotation(PrintMethodName)")
    public void beforePrintMethodName(JoinPoint joinPoint) {
        log.info("beforePrintMethodName {}", joinPoint.getSignature().getName());
    }

}
