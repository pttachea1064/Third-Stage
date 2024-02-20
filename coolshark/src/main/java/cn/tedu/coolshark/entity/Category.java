package cn.tedu.coolshark.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "分類",description = "用於描述商品是屬於哪個分類")
public class Category {
    @ApiModelProperty(value = "分類id",position = 1)
    private Integer id;
    @ApiModelProperty(value = "分類名稱",position = 2)
    private String name;


   }
