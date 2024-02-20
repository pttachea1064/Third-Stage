package cn.tedu.coolshark.controller;

import cn.tedu.coolshark.entity.User;
import cn.tedu.coolshark.mapper.UserMapper;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Api(tags = "5.用戶處理類")

@RequestMapping("user")
@RestController
public class UserController {
    //要求 知道用戶名稱是否存在 以及 密碼是否正確
    @Autowired
    private UserMapper mapper;
    /*由於mapper已經解決名稱是否存在 所以在Controller中解決密碼是否正確*/

//    @RequireLog(operation = "用戶登入")
    @PostMapping("login")
    public int login(@RequestBody User user, HttpSession session){
        User u = mapper.selectByUsername(user.getUsername());
        if (u==null)return 2;//通過Username調用結果為空時 以2表示用戶名不存在
        //user 表示用戶輸入的訊息 包含用戶名稱 和 密碼
        //u 代表數據庫中查詢的訊息結果 包含id 用戶名稱 暱稱 和密碼
        if(u.getPassword().equals(user.getPassword())){
            //把當前登入的用戶對象保存到會話對象中
            session.setAttribute("user",u);
            return 1;//表示密碼正確
        }
        return 3;//表示密碼不正確 但是用戶名稱存在
    }

    @GetMapping("currentUser")
    public User currentUser(HttpSession session){
        /**獲取登入成功時候保存在session中的用戶訊息
         * */
        //用以檢測session是否有將用戶訊息保存
        //取數值時候 會被要求是Object類型
        //但是我們想要用User類型 所以我們需要強制轉換成User類型
        return (User) session.getAttribute("user");
    }

    @GetMapping("logout")
    public void logout(HttpSession session){
        /**將登入成功時候保存之用戶訊息移除
         *
         */
        session.removeAttribute("user");
    }
}
