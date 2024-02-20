package cn.tedu.coolshark;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

//此註解是用在當前類型中所有包與子包下掃描過濾器
@EnableAsync //Spring 容器啟動時候會創建 線程池
@ServletComponentScan
@SpringBootApplication
public class CoolsharkApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoolsharkApplication.class, args);
    }

}
