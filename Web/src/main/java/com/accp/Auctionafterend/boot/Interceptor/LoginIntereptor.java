package com.accp.Auctionafterend.boot.Interceptor;

import com.accp.Auctionafterend.boot.commons.commons.Contants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登入检查
 */
@Slf4j
public class LoginIntereptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURL=request.getRequestURI();
//        String token=ToKenUt
        log.info("准备拦截的URL:"+requestURL);
        //登入检查逻辑
        HttpSession session=request.getSession();
        Object user=session.getAttribute(Contants.SESSION_USERS);
        if(user != null){
            return true;
        }
        session.setAttribute("msg","清先登入");
        response.sendRedirect("http://localhost:8081/");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }

}
