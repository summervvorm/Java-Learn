package org.example.springbootstartersample.config;


import org.example.springbootstartersample.aspect.TraceLogAspect;
import org.example.springbootstartersample.filter.TraceIdFilter;
import org.example.springbootstartersample.thread.TraceThreadPoolExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Description 日志配置
 * @Date 2025/4/15 10:57
 * @Created by raolongxiang
 */
@Configuration
@ConditionalOnWebApplication
@EnableAspectJAutoProxy
public class TraceAutoConfig {
    private final Logger log =  LoggerFactory.getLogger(TraceAutoConfig.class);
    @Bean
    public TraceIdFilter traceIdFilter() {
        return new TraceIdFilter();
    }
    @Bean
    public TraceLogAspect traceLogAspect() {
        log.info("Initializing TraceLogAspect");
        return new TraceLogAspect();
    }
    @Bean("traceThreadPool")
    @ConditionalOnMissingBean
    public TraceThreadPoolExecutor traceThreadPool() {
        return new TraceThreadPoolExecutor(10, 20, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
    }
}