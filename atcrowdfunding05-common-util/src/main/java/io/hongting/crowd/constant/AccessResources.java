package io.hongting.crowd.constant;

import java.util.HashSet;
import java.util.Set;

/**
 * @author hongting
 * @create 2021 10 19 6:01 PM
 */
public class AccessResources {

    public static final Set<String> NON_STATIC_RESOURCES = new HashSet<>();

    static {
      NON_STATIC_RESOURCES.add("/");
      NON_STATIC_RESOURCES.add("/auth/member/login/page.html");
      NON_STATIC_RESOURCES.add("/auth/member/reg/page.html");
      NON_STATIC_RESOURCES.add("/auth/member/sendMessage.json");
      NON_STATIC_RESOURCES.add("/auth/member/register.html");
      NON_STATIC_RESOURCES.add("/auth/member/login.html");
      NON_STATIC_RESOURCES.add("/auth/member/logout.html");
      NON_STATIC_RESOURCES.add("/error");
      NON_STATIC_RESOURCES.add("/favicon.ico");
    }

    public static final Set<String> STATIC_RESOURCES = new HashSet<>();
    
    static {
        STATIC_RESOURCES.add("bootstrap");
        STATIC_RESOURCES.add("css");
        STATIC_RESOURCES.add("fonts");
        STATIC_RESOURCES.add("img");
        STATIC_RESOURCES.add("jquery");
        STATIC_RESOURCES.add("layer");
        STATIC_RESOURCES.add("script");
        STATIC_RESOURCES.add("ztree");
    }
    /**
     * @param servletPath 当前请求的路径  就是localhost:8080/aaa/bbb/ccc中的 “aaa/bbb/ccc”
     * @return true: 表示该资源是静态资源; false: 表示该资源不是静态资源
     */
    public static boolean judgeIsStaticResource(String servletPath){

        // 先判断字符串是否为空
        if (servletPath == null || servletPath.length() == 0){
            throw new RuntimeException(ResultConstant.MESSAGE_STRING_INVALID);
        }

        // 通过“/”来分割得到的请求路径
        String[] split = servletPath.split("/");

        // split[0]是一个空字符串，因此取split[1]，相当于/aaa/bbb/ccc的“aaa”
        String path = split[1];

        // 判断是否包含得到的请求的第一个部分
        return STATIC_RESOURCES.contains(path);
    }
}
