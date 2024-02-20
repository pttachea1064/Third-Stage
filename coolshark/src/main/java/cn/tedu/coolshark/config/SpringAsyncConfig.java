package cn.tedu.coolshark.config;


import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
//讀取Spring 配置文件中以spring.async.task為前綴的數據
@ConfigurationProperties("spring.async.task")
@Setter //利用該註解去獲取下方的成員變量的數值
@Slf4j
public class SpringAsyncConfig implements AsyncConfigurer { //利用接口implements AsyncConfigurer 確定是作用在SpringAsyncConfig中
    //自訂數量 名稱等
    private int corePoolSize = 5;
    private int maxPoolSize = 100;
    private int keepAliveSeconds = 60;
    private int queueCapacity = 128;
    private String threadNamePrefix = "tast===>";


    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setKeepAliveSeconds(keepAliveSeconds);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix(threadNamePrefix);
        //自定义拒绝处理策略
        executor.setRejectedExecutionHandler(new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                log.error("隊列已滿並且暫無可用的線程");
            }
        });
        executor.initialize();//初始化 使其生效
        return executor;
    }

    //當執行時有異常
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
    return new AsyncUncaughtExceptionHandler() {
        @Override
        public void handleUncaughtException(Throwable ex, Method method, Object... params) {
            log.error("任務執行時出現了{}",ex.getMessage()+"錯誤請聯繫相關人員");
        }
    };
    }
}
