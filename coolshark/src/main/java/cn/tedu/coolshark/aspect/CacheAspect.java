package cn.tedu.coolshark.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.sun.corba.se.impl.util.RepositoryId.cache;

@Component
@Aspect
public class CacheAspect {

    //建一個緩存
    private Map<Object,Object> cache = new ConcurrentHashMap<>();


    @Pointcut("@annotation(cn.tedu.coolshark.annotation.RequireCache)")//利用CopyRefrence
    public void doCache(){}

    @Pointcut("@annotation(cn.tedu.coolshark.annotation.ClearCache)")
    public void doClear() {}

    @AfterReturning("doClear()")
    public void doAfterReturing(){
        cache.clear();
    }

    //簡化寫法
    //@Around("@annotation(cn.tedu.coolshark.annotation.RequireCache)")
    //上方的Pointcut不用寫時的表示
    @Around("doCache()")
    public Object doAround(ProceedingJoinPoint jp) throws Throwable {
        System.out.println("----CacheAspect----");
        //從緩存中取得數值 如若沒有數值 則查詢數據庫
        System.out.println("Get Data from Cache");
        Object result = cache.get("dataKey");
        //如果不為空執行下方
        if (result != null) return  result;
        //如果為空則執行下方
        result = jp.proceed();//執行目標方法(查詢方法)
        System.out.println("Put Data to Cache");
        cache.put("dateKey",result);
        return result;

    }


}
