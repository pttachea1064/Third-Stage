package cn.tedu.coolshark.mapper;

import cn.tedu.coolshark.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    //要求 知道用戶名稱是否存在 以及 密碼是否正確

    //解決了用戶名稱是否存在的問題 因為是藉由用戶名稱查詢
    @Select("select id ,username , password, nickname from user where username = #{username}")
    User selectByUsername(String username);
}
