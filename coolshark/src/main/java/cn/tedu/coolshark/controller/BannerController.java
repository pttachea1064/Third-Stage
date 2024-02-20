package cn.tedu.coolshark.controller;

import cn.tedu.coolshark.annotation.RequireCache;
import cn.tedu.coolshark.annotation.RequiredLog;
import cn.tedu.coolshark.entity.Banner;
import cn.tedu.coolshark.mapper.BannerMapper;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;
@Api(tags = "1.輪播處理類")
@RestController
@RequestMapping("banner")//外加內容 與筆記不同 將路徑提出
public class BannerController {
    @Autowired
    private BannerMapper mapper;

    @RequiredLog(operation = "查詢輪放圖")
    @GetMapping("/list")
    public List<Banner> list(){
        return mapper.list();
    }

    @RequiredLog(operation = "刪除輪放圖")
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") int id){
        //得到删除轮播图的图片名
        String url = mapper.selectUrlById(id);
        //得到文件的完整路径
        String filePath = "D:/file/"+url;
        //删除文件
        new File(filePath).delete();

        mapper.deleteById(id);
    }

    @RequireCache
    @RequiredLog(operation = "增加輪放圖")
    @PostMapping("/insert")
    public void insert(@RequestBody Banner banner){
        System.out.println("banner = " + banner);
        mapper.insert(banner);
    }




}
