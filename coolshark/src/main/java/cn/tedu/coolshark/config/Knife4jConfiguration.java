package cn.tedu.coolshark.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;


/**
 * EnableSwagger2WebMvc表示是一個knife4j的配置類
 */
@Configuration
@EnableSwagger2WebMvc
public class Knife4jConfiguration {

    //用此方法去創建Api的應用
    @Bean
    //使用別人訂的技術框架 我們要去符合他們制定的規則
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                //創建接口文件檔案的具體訊息
                .apiInfo(apiInfo())
                //創建選擇器去控制那些接口被加入文件檔案中
                .select()
                //指定此包下方的接口被加入文件檔案
                .apis(RequestHandlerSelectors.basePackage("cn.tedu.coolshark.controller"))//重點
                //允許匹配所有的路徑
                .paths((PathSelectors.any()))
                .build();
    }

    //在此方法中創建gaiApi的基本訊息(這些基本訊息會展示在文檔案畫面中
    private ApiInfo apiInfo(){
        return  new ApiInfoBuilder()
                //文件檔案標題
                .title("接口文件檔案標題")
                //文件檔案描述
                .description("文件檔案內容描述")
                //服務條款URL
                .termsOfServiceUrl("http://www.xxx.com")
                //聯繫人/負責人 訊息
                .contact(new Contact("yu-ming","http://yuming.com","yuming@qq.com"))
                //開發的版本
                .version("1.0")
                .build();

    }

}
