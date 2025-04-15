package org.example.springbootstartersample.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.example.springbootstartersample.annotation.TraceLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;



/**
 * @Description 日志切面
 * @Date 2025/4/15 10:27
 * @Created by raolongxiang
 */
@Aspect
@Component
//@Slf4j
public class TraceLogAspect {

    private static final Logger log = LoggerFactory.getLogger(TraceLogAspect.class); // 手动初始化Logger

    @Around("@annotation(traceLog)")
    public Object around(ProceedingJoinPoint pjp, TraceLog traceLog) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            Object result = pjp.proceed();
            log.info("[{}] method={}, time={}ms", traceLog.value(),
                    pjp.getSignature().getName(), System.currentTimeMillis()-start);
            return result;
        } catch (Exception e) {
            log.error("[{}] method={} error: {}", traceLog.value(),
                    pjp.getSignature().getName(), e.getMessage());
            throw e;
        }
    }
}