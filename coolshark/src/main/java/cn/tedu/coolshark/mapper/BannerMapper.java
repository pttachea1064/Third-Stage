package cn.tedu.coolshark.mapper;

import cn.tedu.coolshark.entity.Banner;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Mapper
public interface BannerMapper {
    /**
     * 查询Banner表中所有的Banner信息,然后每行记录映射为一个Banner对象，
     * 多个Banner存储到List集合。
     *
     * @Select注解用于描述方法并定义SQL查询语句。
     */
    @Select("select id,url from banner")
    List<Banner> list();

    /**
     * 基于id查询Banner对应的url。
     * 参数的id值会赋值给sql语句中的#{id},这里的#{}为一个占位符，
     * 最终这部分内容会被替换为?占位符
     */
    @Select("select url from banner where id=#{id}")
    String selectUrlById(int id);

    /**
     * 向表中添加一条新的记录。
     *
     * @Insert注解用于定义插入语句
     */
    @Insert("insert into banner values(null,#{url})")
    void insert(Banner banner);

    /**
     * 基于id删除banner表中指定记录。
     *
     * @Delete注解用于定义删除语句
     */
    @Delete("delete from banner where id=#{id}")
    void deleteById(int id);
}
