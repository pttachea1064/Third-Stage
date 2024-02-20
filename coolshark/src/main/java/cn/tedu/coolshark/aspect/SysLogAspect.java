package cn.tedu.coolshark.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @Aspect 註解描述的類型為一個切面類型,這樣的切面類型中通常會定義兩部分內容
 * 1.切入點:切入拓展功能的點(例如:業務對象中的一個或多個方法)
 * 2.通知:再切入點在對應的方法執行時要寫入的拓展功能
 */
@Aspect
@Slf4j
@Component
public class SysLogAspect {
    /**
     * @Pointcut 注解一般用于描述方法，在方法上定义切入点。
     * bean(productController) 为一个切入点表达式
     * 通過切入點來去找要為誰添加額外功能
     */
    @Pointcut("bean(productController)")//切入點
    public void logPointCut() {}//方法内部不需要写任何内容 乘載作用的方法

    /**
     * 通知,在通知中写额外增强功能的代码
     * 藉由@Around來寫額外增強的功能代碼
     */
    @Around("logPointCut()")
    //攔截切入點並處理(連接點)
    public Object around(ProceedingJoinPoint jp)throws Throwable{
        //记录方法执行时的开始时间
        long t1=System.currentTimeMillis();
        try {
            //调用目标方法
            Object result=jp.proceed();//调用本切面中其它通知或下一个切面的通知或目标方法
            //记录方法执行的结束时间以及总时长。
            long t2=System.currentTimeMillis();
            log.info("method execute time {}",(t2-t1));
            return result;
        }catch(Throwable e) {
            //出现异常时还要输出错误日志。
            log.error("error is {} ",e.getMessage());
            throw e;
        }
    }

}
