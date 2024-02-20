package cn.tedu.coolshark.service.impl;

import cn.tedu.coolshark.entity.Log;
import cn.tedu.coolshark.mapper.LogMapper;
import cn.tedu.coolshark.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
//聲明說這是一個Service層級 ,需要創建對象給Spring容器管理
public class LogServiceImpl implements LogService {
    @Autowired
    private LogMapper logMapper;

    @Async
    @Transactional(propagation = Propagation.REQUIRED )
    @Override
    public void insert(Log log) {
        System.out.println(Thread.currentThread().getName()+"--->LogServiceImpl.insert");
        try{Thread.sleep(5000);}catch (Exception e){}
        System.out.println(log);
        logMapper.insert(log);
    }
}
