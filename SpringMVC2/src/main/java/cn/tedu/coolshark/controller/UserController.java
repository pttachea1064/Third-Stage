package cn.tedu.coolshark.controller;

import cn.tedu.coolshark.dao.UserDao;
import cn.tedu.coolshark.pojo.JsonResult;
import cn.tedu.coolshark.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController // @ResponseBody +@Controller
public class UserController {
    @Autowired
    private UserDao userDao;
    /*用戶註冊(做添加)*/
    @PostMapping("user/register")//用PostMapping 做請求定義路徑
    public JsonResult doRegister(@RequestBody User user){
        user.setCreatedTime(new Date());
        user.setModifiedTime(new Date());//這兩個時間由後台直接建立時候增加

        userDao.insert(user);
        return new JsonResult("註冊成功!");

    }

    @GetMapping("user/list/{createdTime}")
    public JsonResult dolist(@PathVariable String createdTime){
        List<User> list = userDao.list(createdTime);
        return new JsonResult(list);
    }

    @PutMapping("user/update")
    public JsonResult doUpdate(@RequestBody User user){
        user.setModifiedTime(new Date());
        userDao.update(user);
        return new JsonResult("更新成功");
    }
}
