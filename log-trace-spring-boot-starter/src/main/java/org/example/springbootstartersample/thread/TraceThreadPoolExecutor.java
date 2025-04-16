package org.example.springbootstartersample.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.Map;
import java.util.concurrent.*;


/**
 * @Description 线程池
 * @Date 2025/4/15 10:37
 * @Created by raolongxiang
 */
public class TraceThreadPoolExecutor extends ThreadPoolExecutor {
    private static final Logger log = LoggerFactory.getLogger(TraceThreadPoolExecutor.class);

    public TraceThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public TraceThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    public TraceThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
    }

    public TraceThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }

    // 重写执行方法，传递上下文
    @Override
    public void execute(Runnable task) {
        Map<String, String> context = MDC.getCopyOfContextMap();
        super.execute(() -> {
            log.info("traceId:{}", MDC.get("traceId"));
            MDC.setContextMap(context);
            task.run();
        });
    }
}