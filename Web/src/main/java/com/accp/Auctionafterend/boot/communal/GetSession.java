package com.accp.Auctionafterend.boot.communal;

import com.accp.Auctionafterend.boot.commons.commons.Contants;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class GetSession {
    /**
     * 获取session对象
     * @return
     */
    public static Object Session(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session=request.getSession();
        return session.getAttribute(Contants.SESSION_USERS);
    }
}
