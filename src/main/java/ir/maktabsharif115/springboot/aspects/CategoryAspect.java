package ir.maktabsharif115.springboot.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Aspect
@Slf4j
public class CategoryAspect {

    //    @Pointcut("within(ir.maktabsharif115.springboot.service.CategoryService+)")
    @Pointcut(
            """
                    execution(public void ir.maktabsharif115.springboot.service.CategoryService.testAspect()) ||
                    execution(public Long ir.maktabsharif115.springboot.service.CategoryService.testAspect(Long))
                    """
    )
    public void logMethodName() {
    }

    @Around("logMethodName()")
    public Object around(ProceedingJoinPoint joinPoint) {
        beforeExecution(joinPoint);
        Object proceed = null;
        try {
            proceed = joinPoint.proceed();
            afterReturning(proceed, joinPoint);
        } catch (Throwable e) {
            afterThrowing(joinPoint);
        }
        return proceed;
    }

    private void beforeExecution(ProceedingJoinPoint joinPoint) {
        log.info("beforeExecution");
    }

    private void afterReturning(Object proceed, ProceedingJoinPoint joinPoint) {
        log.info("afterReturning");
    }

    private void afterThrowing(ProceedingJoinPoint joinPoint) {
        log.info("afterThrowing");
    }

}
