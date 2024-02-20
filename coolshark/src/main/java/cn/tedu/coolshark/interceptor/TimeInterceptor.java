package cn.tedu.coolshark.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;

public class TimeInterceptor implements HandlerInterceptor {//攔截器
    //業務需求 在幾點前不能進行登入 以及幾點後不能進行登入
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //執行前進行攔截
        System.out.println("preHandle()");
        //獲取java中的日曆物件
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY,6);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.SECOND,0);
        long start = c.getTimeInMillis();
        c.set(Calendar.HOUR_OF_DAY,22);
        //因為是整點 所以MINUTE 和 SECOND可以不用寫 但是因為上方是為了基礎所以有寫
        long end = c.getTimeInMillis();
        long cTime = System.currentTimeMillis();
        if (cTime<start||cTime>end)throw new RuntimeException("不在訪問時間內");
        return true;//表示放行
    }
}
