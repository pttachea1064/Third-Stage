package cn.tedu.coolshark.mapper;

import cn.tedu.coolshark.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface HttpMapper {

    @Select("select * from user")
    List<User> getUserAll();

    @Select("select * from user where id = #{id}")
    User getUser(int id);

    @Insert("Insert Into user values(null,#{username},#{password},#{nickname})")
    void postUser(User user);


    @Update("update user set username=#{username},password=#{password},nickname=#{nickname} where id = #{id}")
    void putUser(User user);

    @Delete("delete from user where id = #{id}")
    void deleteUser(int id);

}
