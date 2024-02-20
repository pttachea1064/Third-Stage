package cn.tedu.coolshark.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
@Data //利用該註解 去達成get set toString 的實現
@ApiModel(value="商品類",description = "用於描述商品訊息")
public class Product {
    @ApiModelProperty(value = "商品id",position = 1)
    private Integer id;
    /**商品标题*/
    @ApiModelProperty(value = "商品标题",position = 2)
    private String title;
    /**商品url*/
    @ApiModelProperty(value = "商品图片",position = 3)
    private String url;
    /**商品价格*/
    @ApiModelProperty(value = "商品价格",position = 4)
    private Double price;
    /**商品原价*/
    @ApiModelProperty(value = "商品原价",position = 5)
    private Double oldPrice;
    /**商品浏览量*/
    @ApiModelProperty(value = "商品浏览量",position = 6)
    private Integer viewCount; //浏览量
    /**商品销量*/
    @ApiModelProperty(value = "商品销量",position = 7)
    private Integer saleCount;
    /**商品发布时间*/
    @ApiModelProperty(value = "商品发布时间",position = 9)
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss",timezone = "GMT+8")
    private Date created;  //发布时间  导包java.util
    /**商品分类*/
    @ApiModelProperty(value = "商品分类",position = 8)
    private Integer categoryId; //商品分类id


}
