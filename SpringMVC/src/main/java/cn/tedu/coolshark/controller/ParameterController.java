package cn.tedu.coolshark.controller;

import cn.tedu.coolshark.entity.Emp;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

//@Controller
//@ResponseBody //保證這個類中的每個方法都是字符串類型的方法才可放
@RestController //即 @Controller + @ResponseBody
public class ParameterController {

    @RequestMapping("param1")
    public String param1(String name){
        return "姓名為:"+name;
    }
    @RequestMapping("param2")
    public String param2(String name,int age){
        return "姓名為:"+name+",年齡為:"+age;
    }

    @RequestMapping("param3")
    public String param3(Emp emp){
        return emp.toString();
    }

    @RequestMapping("param4")
    public String param4(Integer[] ids){
        return Arrays.toString(ids);
    }
}
