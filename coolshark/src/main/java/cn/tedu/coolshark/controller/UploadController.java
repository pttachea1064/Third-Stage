package cn.tedu.coolshark.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
@Api(tags = "4.上傳處理類")

@RestController
public class UploadController {
    //定義路徑
    private String dirPath = "D:/file";
    //定義方法

    @PostMapping("upload")
    public String upload(MultipartFile picFile) throws IOException {
        //得到原始文件名稱 (如果有相同的文件名稱 會被覆蓋掉 所以我們取得原本的文件名稱後去修改名稱)
        String fileName = picFile.getOriginalFilename();
        //得到後綴名稱(保證正常運行的後綴 ex. .jpg .txt .....)
        /**
         //        利用lastIndexOf找出最後的一個"."的下標數值
         //         */
        String suffix = fileName.substring(fileName.lastIndexOf("."));

        //修改文件名稱 (利用UUID.randomUUID()方法去隨機獲取名稱)
        fileName = UUID.randomUUID() + suffix;
        //得到文件完整路徑
        File dirFile = new File(dirPath);
        if(!dirFile.exists()){
            //創建依級別
            dirFile.mkdirs();
        }
        //D:file/a.jpg
        String filePath = dirPath+"/"+fileName;

        //保存文件到此路徑中
        picFile.transferTo(new File(filePath));

        //返回文件名稱 刪除時使用此文件名稱
        return fileName;
    }

    @DeleteMapping("remove")
    public void remove(String name){
        String filePath = dirPath+"/"+name;
        File file = new File(filePath);
        if (file.exists()){
            file.delete();//刪除文件
        }
    }

}
