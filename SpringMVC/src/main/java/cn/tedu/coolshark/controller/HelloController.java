package cn.tedu.coolshark.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller //標示此類為Controller層級 並且創建對象交給Spring容器管理
public class HelloController {
    //http://localhost:8080/hello
    @RequestMapping("hello")//訪問路徑
    public String doHelloUI(){//返回的是頁面
        //返回给调用方,这里是DispatcherServlet,DispatcherServlet会将hello字符串交给视图解析器,
        // thymeleaf中的视图解析器会对hello字符串添加前缀和后缀
        return "hello";
    }
    //http://localhost:8080/doSayHello
    @RequestMapping("doSayHello")
    @ResponseBody //告訴SpringMVC框架返回數值不是view而是具體的數據
    public String doSayHello(){
        //因為是數據所以要添加@ResponseBody 返回數據方法
        //返回的是數據
        return "Hello SpringMVC";
    }
    //http://localhost:8080/login
    @RequestMapping("login")
    @ResponseBody
    public String login(){
        return "登入成功"+"http://localhost:8080/hello";
    }
}
