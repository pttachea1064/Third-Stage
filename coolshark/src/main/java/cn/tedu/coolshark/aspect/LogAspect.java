package cn.tedu.coolshark.aspect;

import cn.tedu.coolshark.annotation.RequiredLog;
import cn.tedu.coolshark.annotation.RequiredLog;
import cn.tedu.coolshark.entity.Log;
import cn.tedu.coolshark.entity.User;
import cn.tedu.coolshark.service.LogService;
import cn.tedu.coolshark.entity.Log;
import cn.tedu.coolshark.entity.User;
import cn.tedu.coolshark.service.LogService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/*
 * 由Aspect注解描述的类型为一个切面类型，此类型中可以定义
 * 1)切入点(要切入扩展业务逻辑的一些方法集合)
 * 2)通知方法(拓展业务逻辑的实现)
 */
@Order(1)
@Slf4j
@Aspect
@Component
public class LogAspect {
    /*
     * 通过@Around注解描述功能拓展方法，表示我们在此方法内部可以为目标业务方法进行
     * 功能拓展，可以在目标业务执行之前，也可以在目标业务执行之后，添加拓展业务逻辑
     */
    @Around("@annotation(cn.tedu.coolshark.annotation.RequiredLog)")
    public Object doLogAround(ProceedingJoinPoint jp) throws Throwable {
        System.out.println("--- LogAspect ---");
        int status = 1; //初始状态
        String error = null; //初始错误信息
        long time = 0L; //耗时
        long t1 = System.currentTimeMillis();
        try {
            Object result = jp.proceed();
            long t2 = System.currentTimeMillis();
            time = t2 - t1;
            return result;
        } catch (Throwable e) {
            long t3 = System.currentTimeMillis();
            time = t3 - t1;
            status = 0; //表示出错了
            error = e.getMessage();
            throw e;
        }finally {
            saveLog(jp,time,status,error);
        }
    }

    private void saveLog(ProceedingJoinPoint jp, long time, int status, String error) throws Exception {
        //1.获取用户行为日志
        //1.1获取ip地址
        //获取当前线程的请求属性
        ServletRequestAttributes requestAttributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String ip = request.getRemoteAddr();
        //1.2获取登录用户
        String username = null;
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            username = user.getUsername();
        }
        //1.3获取操作名(註解中的operation名稱)
        //1.3.1获取类的字节码对象
        Class<?> targetCls = jp.getTarget().getClass();
        //1.3.2获取方法签名对象
        MethodSignature ms = (MethodSignature) jp.getSignature();
        //1.3.3获取目标方法对象
        Method targetMethod = targetCls.getDeclaredMethod(ms.getName(),//方法名
                ms.getParameterTypes()//参数列表类型
        );
        //1.3.4获取方法上的注解
        RequiredLog requiredLog = targetMethod.getAnnotation(RequiredLog.class);
        //1.3.5获取操作名
        String operation = requiredLog.operation();
        //1.4获取目标方法名(类全名+方法名)
        String method = targetCls.getName()+"."+targetMethod.getName();
        //1.5获取参数
        String params = new ObjectMapper().writeValueAsString(jp.getArgs());
        //2.封装用户行为日志
        Log userLog = new Log();
        userLog.setIp(ip);
        userLog.setUsername(username);
        userLog.setCreatedTime(new Date());
        userLog.setOperation(operation);
        userLog.setMethod(method);
        userLog.setParams(params);
        userLog.setStatus(status);
        userLog.setError(error);
        userLog.setTime(time);
        //3.记录用户行为日志
        log.info("user log is {}",userLog);
        logService.insert(userLog);
    }

    @Autowired
    private LogService logService;
}
