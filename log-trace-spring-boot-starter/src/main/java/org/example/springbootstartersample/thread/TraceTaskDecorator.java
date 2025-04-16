package org.example.springbootstartersample.thread;

import org.slf4j.MDC;
import org.springframework.core.task.TaskDecorator;

import java.util.Map;

/**
 * @Description
 * @Date 2025/4/15 10:37
 * @Created by raolongxiang
 */
public class TraceTaskDecorator implements TaskDecorator {
    @Override
    public Runnable decorate(Runnable runnable) {
        // 获取父线程的 MDC 上下文
        Map<String, String> contextMap = MDC.getCopyOfContextMap();
        return () -> {
            try {
                // 将父线程的上下文设置到子线程
                if (contextMap != null) {
                    MDC.setContextMap(contextMap);
                }
                runnable.run();
            } finally {
                MDC.clear(); // 确保清除残留数据
            }
        };
    }
}