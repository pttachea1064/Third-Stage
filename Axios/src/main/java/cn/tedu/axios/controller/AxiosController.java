package cn.tedu.axios.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AxiosController {
    @GetMapping("hello1Axios")
    public String hello1(String info) {
        return "请求成功! info="+info;
    }

    @PostMapping("hello2Axios")
    public String hello2(@RequestBody String info){
        return "請求成功 ! info ="+info;
    }

}
