package cn.tedu.coolshark.controller;

import cn.tedu.coolshark.entity.User;
import cn.tedu.coolshark.mapper.HttpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Http2Controller {

    @Autowired
    private HttpMapper httpMapper;

    /**
     * 在RESTful 風格中 使用@GetMapping
     * 與先前的 @RequestMapping 不同
     */
    @GetMapping("user")
    public String getUserAll(){
        List<User> list = httpMapper.getUserAll();
        return list.toString();
    }

    /**
     *  localhost:8080/getUserById?id=1
     *  RESTful風格 localhost:8080/getUserById/1
     *
     *  GetMapping(value="user/{id}) 直接獲取要獲取的數據值
     * @PathVariable 表示獲取請求路徑中參數的數值 要求必須要同名 即GetMapping()中的{}
     * 會等同於方法中的參數 名稱
     */
    @GetMapping("user/{id}")
    public User getUser(@PathVariable int id){
        User user = httpMapper.getUser(id);
        return user;
    }

    @DeleteMapping("user/{id}")
    public String deleteUser(@PathVariable int id){
        httpMapper.deleteUser(id);
        return "刪除成功";
    }
    @PostMapping("user")
    public String postUser(@RequestBody User user){
        httpMapper.postUser(user);
        return "新增成功";
    }

    @PutMapping("user")
    public String putUser(@RequestBody User user){
        httpMapper.putUser(user);
        return "更新成功";
    }

}