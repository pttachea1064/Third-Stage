package cn.tedu.coolshark.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

//@Setter
//@Getter
//@ToString
//@AllArgsConstructor
//@NoArgsConstructor
@Data
@ApiModel(value = "輪播圖片類",description = "描述輪播圖訊息")
public class Banner {
    @ApiModelProperty(value = "輪播圖id",position = 1)
    private Integer id;
    @ApiModelProperty(value = "輪播圖來源",position = 2)
    private String url;



}
