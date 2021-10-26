package io.hongting.crowd.mvc.config;

import com.google.gson.Gson;
import io.hongting.crowd.constant.ResultConstant;
import io.hongting.crowd.exception.LoginAcctEditException;
import io.hongting.crowd.exception.LoginAcctExistedException;
import io.hongting.crowd.exception.LoginFailedException;
import io.hongting.crowd.util.R;
import io.hongting.crowd.util.Util;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author hongting
 * @create 2021 09 27 11:57 AM
 */

@ControllerAdvice
public class UnifiedExceptionHandler {


    @ExceptionHandler(NullPointerException.class)
    public ModelAndView handleException(NullPointerException exception, HttpServletRequest request, HttpServletResponse response) throws IOException {
        return handleServletException("system-error", request , response, exception);
    }

    @ExceptionHandler(ArithmeticException.class)
    public ModelAndView handleException(ArithmeticException exception, HttpServletRequest request, HttpServletResponse response) throws IOException {
        return handleServletException("system-error", request , response, exception);
    }

    @ExceptionHandler(LoginFailedException.class)
    public ModelAndView handleException(LoginFailedException exception, HttpServletRequest request, HttpServletResponse response) throws IOException {
        return handleServletException("admin-login", request , response, exception);
    }

    @ExceptionHandler(LoginAcctExistedException.class)
    public ModelAndView handleException(LoginAcctExistedException exception, HttpServletRequest request, HttpServletResponse response) throws IOException {
        return handleServletException("admin-add", request , response, exception);
    }

    @ExceptionHandler(LoginAcctEditException.class)
    public ModelAndView handleException(LoginAcctEditException exception, HttpServletRequest request, HttpServletResponse response) throws IOException {
        return handleServletException("admin-edit", request , response, exception);
    }

    public ModelAndView handleServletException (String viewName, HttpServletRequest request, HttpServletResponse response, Exception exception) throws IOException {
        boolean requestType = Util.judgeRequestType(request);
        if (requestType){
            R<Object> r = R.fail(exception.getMessage());
            Gson gson = new Gson();
            String json = gson.toJson(r);
            response.getWriter().write(json);
            return null;
        }else{
            ModelAndView view = new ModelAndView();
            view.setViewName(viewName);
            view.addObject(ResultConstant.ATTR_NAME_EXCEPTION, exception);
            return view;
        }

    }
}
