package org.example.springbootstartersample.controller;


import jakarta.annotation.PostConstruct;
import org.example.springbootstartersample.annotation.TraceLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 测试控制层
 * @Date 2025/4/16 14:01
 * @Created by raolongxiang
 */
@RestController
@RequestMapping("/test")
public class TestController {
    private static final Logger log = LoggerFactory.getLogger(TestController.class);

    @GetMapping("/hello")
    @TraceLog(value = "test")
    public String hello() {
        log.info("Async task traceId: {}", MDC.get("traceId")); // 正确输出父线程的traceId
        return "hello";
    }

}
