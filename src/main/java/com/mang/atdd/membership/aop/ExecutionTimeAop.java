package com.mang.atdd.membership.aop;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
@Log4j2
public class ExecutionTimeAop {

    @Around("@within(com.mang.atdd.membership.aop.ExecutionTimeChecker)")
    public Object calculateExecutionTime(final ProceedingJoinPoint pjp) throws Throwable {
        // 해당 클래스 처리 전의 시간
        final StopWatch sw = new StopWatch();
        sw.start();

        // 해당 클래스의 메소드 실행
        final Object result = pjp.proceed();

        // 해당 클래스 처리 후의 시간
        sw.stop();
        final long executionTime = sw.getTotalTimeMillis();

        final String className = pjp.getTarget().getClass().getName();
        final String methodName = pjp.getSignature().getName();
        final String task = className + "." + methodName;

        log.debug("[ExecutionTime] " + task + "-->" + executionTime + "(ms)");

        return result;
    }

}