package org.example.springbootstartersample.filter;

import jakarta.servlet.*;
import org.slf4j.MDC;

import java.io.IOException;
import java.util.UUID;



/**
 * @Description 过滤器
 * @Date 2025/4/15 10:32
 * @Created by raolongxiang
 */

public class TraceIdFilter implements Filter {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(TraceIdFilter.class);


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        String traceId = UUID.randomUUID().toString();//todo 这里后期可以改为自定义
        log.info("TraceIdFilter doFilter traceId: {}", traceId);
        MDC.put("traceId", traceId);  // 使用MDC存储
        chain.doFilter(request, response);
        MDC.clear();
    }
}