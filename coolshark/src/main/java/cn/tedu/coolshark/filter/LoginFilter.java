package cn.tedu.coolshark.filter;

import cn.tedu.coolshark.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "loginFilter",urlPatterns = {"/product/insert"})
public class LoginFilter implements Filter {
    /**
     * 当我们访问/product/insert时，请求会被当前过滤器拦截。
     */
    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        System.out.println("过滤器执行了");
        //1.获取HttpServletRequest，HttpServletResponse对象
        HttpServletRequest request=(HttpServletRequest) servletRequest;
        HttpServletResponse response=(HttpServletResponse) servletResponse;
        //2.获取服务端的HttpSession对象
        HttpSession session = request.getSession();
        //3.从session对象中获取登陆user对象
        User user=(User) session.getAttribute("user");
        if(user==null){//假如这里为null，说明用户还未登陆，没有登陆则跳转到登陆页面
            //response.sendRedirect("/login.html");
            System.out.println("未登录!");
            return;
        }
        //4.假如用户登陆了，则执行下一步操作(允许访问资源)。
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
