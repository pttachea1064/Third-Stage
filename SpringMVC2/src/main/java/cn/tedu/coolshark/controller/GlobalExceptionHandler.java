package cn.tedu.coolshark.controller;

import cn.tedu.coolshark.pojo.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@ControllerAdvice
//@ResponseBody

/**
 * RestControllerAdvice 註解描述得類 為全局異常處理類型 啟動時會交給Spring管理
 */
@RestControllerAdvice //上方兩個加總
public class GlobalExceptionHandler {//添加日志對象
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /*@ExceptionHandler 註解描述的方法為異常處理方法 註解中定義的異常類行為方法可以處的異常類型*/
    @ExceptionHandler(RuntimeException.class)
    public JsonResult doHandleRuntimeException(RuntimeException e){
        e.printStackTrace();
        log.error("exception msg is {}",e.getMessage());
        return new JsonResult(e);
    }

    //可以定義多個異常處理方法 所以這個類中會有很多異常解決的方法可以寫

}
