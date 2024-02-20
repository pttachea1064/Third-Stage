package cn.tedu.coolshark.controller;

import cn.tedu.coolshark.entity.User;
import cn.tedu.coolshark.mapper.HttpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HttpController {

    @Autowired
    private HttpMapper httpMapper;

    /**
     * value 屬性 表示訪問路徑
     * method 屬性 表示請求的方式
     */
    @RequestMapping(value = "getUser",method= RequestMethod.GET)
    public String getUsrAll(){
        List<User> list = httpMapper.getUserAll();
        return list.toString();
    }

    @RequestMapping(value = "/getUserById",method=RequestMethod.GET)
    public User getUser(int id ){
        User user = httpMapper.getUser(id);
        return user;
    }

    @RequestMapping(value= "/saveUesr",method = RequestMethod.POST)
    public String postUser(@RequestBody User user){
        httpMapper.postUser(user);
        return "添加成功";
    }

    @RequestMapping(value = "/updateUser",method = RequestMethod.PUT)
    public String putUser(@RequestBody User user){
        httpMapper.putUser(user);
        return "更新成功";
    }

    @RequestMapping(value = "/deleteUser",method = RequestMethod.DELETE)
    public String deleteUser(int id){
        httpMapper.deleteUser(id);
        return "删除成功";
    }

}
