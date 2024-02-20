package cn.tedu.coolshark.controller;

import cn.tedu.coolshark.cache.CacheConstants;
import cn.tedu.coolshark.entity.Category;
import cn.tedu.coolshark.mapper.CategoryMapper;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;


import java.util.List;
@Api(tags = "2.商品分類處理類")

@RestController
//@RequestMapping("category")
public class CategoryController {
    @Autowired
    private CategoryMapper mapper;

    //添加缓存,value属性为添加到的缓存名字,sync表示异步
    @Cacheable(value = CacheConstants.CACHE2,sync = true)
    @GetMapping("/category/list")
    public List<Category> list(){
        return mapper.list();
    }
    @DeleteMapping("/category/delete/{id}")
    public void delete(@PathVariable int id){
        mapper.deleteById(id);
    }

    @CacheEvict(value  = CacheConstants.CACHE2,allEntries = true)
    @PostMapping("/category/insert")
    public void insert(@RequestBody Category category){
        mapper.insert(category);
    }

}
