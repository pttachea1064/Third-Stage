package cn.tedu.coolshark.service;

import cn.tedu.coolshark.entity.Log;
import org.apache.ibatis.annotations.Mapper;

public interface LogService {
    void insert (Log log);
}
