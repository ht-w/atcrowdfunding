package io.hongting.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author hongting
 * @create 2021 10 17 1:50 PM
 */

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/auth/member/reg/page.html").setViewName("member-reg");
        registry.addViewController("/auth/member/login/page.html").setViewName("login");
        registry.addViewController("/auth/member/center/page.html").setViewName("member-center");
        registry.addViewController("/auth//member/crowd/page.html").setViewName("member-crowd");
        registry.addViewController("/agree/protocol/page.html").setViewName("project-start");
        registry.addViewController("/launch/page.html").setViewName("project-launch");
    }
}
