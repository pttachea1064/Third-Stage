package cn.tedu.coolshark;

import cn.tedu.coolshark.entity.Banner;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j //添加LOG日志對象
@SpringBootTest
public class LombokTests {

    @Test
    public void test(){
        Banner banner= new Banner();
        banner.setId(10);
        System.out.println(banner.getId());

        log.info(banner.toString());


    }


}
