package cn.tedu.coolshark.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "用戶類",description = "描述用戶的訊息")
public class User {
    @ApiModelProperty(value = "用戶id",position = 1)
    private Integer id;
    @ApiModelProperty(value = "用戶名稱",position = 2)
    private String username;
    @ApiModelProperty(value = "用戶密碼",position = 3)
    private String password;
    @ApiModelProperty(value = "用戶暱稱",position = 4)
    private String nickname;
}
