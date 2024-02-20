package cn.tedu.coolshark.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Log {
    private Long id;
    private String ip;
    private String username;
    private Date createdTime;
    private String operation;
    private String method;
    private String params;
    private Long time;
    private Integer status;
    private String error;
}
