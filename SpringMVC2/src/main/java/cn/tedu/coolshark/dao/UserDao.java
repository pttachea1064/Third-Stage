package cn.tedu.coolshark.dao;

import cn.tedu.coolshark.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserDao {
    /*添加新的User訊息*/
    @Insert("insert into tb_users values (null,#{username},#{nickname},#{password},#{mobile},#{status},#{createdTime},#{modifiedTime})")
    void insert(User user);


    /**查询注册时间比createdTime晚的用户记录*/
    // 註冊(創建/新增)時間 比 手動輸入的時間(比如出生年齡)晚
    @Select("select id , username, nickname, password, mobile, status, created_time , modified_time from tb_users where created_time> #{createTime}")
    List<User> list(String createdTime);

    /**更新用户信息*/
    @Update("update tb_users set username=#{username},password=#{password},nickname=#{nickname},mobile=#{mobile},modified_time=#{modifiedTime} where id = #{id}")
    void update(User user);
}

