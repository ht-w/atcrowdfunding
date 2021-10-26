package io.hongting.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.hongting.crowd.constant.AccessResources;
import io.hongting.crowd.constant.ResultConstant;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author hongting
 * @create 2021 10 19 6:16 PM
 */

@Component
public class AccessFilter extends ZuulFilter {


    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        String servletPath = request.getServletPath();
        boolean containsNonStatic = AccessResources.NON_STATIC_RESOURCES.contains(servletPath);
        if (containsNonStatic){
            return false;
        }
        boolean containsStatic  = AccessResources.judgeIsStaticResource(servletPath);
        return !containsStatic;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        HttpSession session = request.getSession();
        Object attribute = session.getAttribute(ResultConstant.ATTR_NAME_LOGIN_MEMBER);
        if (attribute==null){
            HttpServletResponse response = currentContext.getResponse();
            session.setAttribute("message", ResultConstant.MESSAGE_ACCESS_FORBIDDEN);

            try {
                response.sendRedirect("/auth/member/login/page.html");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;

    }
}
