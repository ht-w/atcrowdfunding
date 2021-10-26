package io.hongting.crowd.mvc.interceptor;

import io.hongting.crowd.constant.ResultConstant;
import io.hongting.crowd.entity.Admin;
import io.hongting.crowd.exception.AccessForbiddenException;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author hongting
 * @create 2021 09 27 5:45 PM
 */


public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        HttpSession session = httpServletRequest.getSession();
        Admin admin = (Admin) session.getAttribute(ResultConstant.ATTR_NAME_LOGIN_ADMIN);
        if(admin == null){
            throw new AccessForbiddenException(ResultConstant.MESSAGE_ACCESS_FORBIDDEN);
        }
        return true;
    }
}
