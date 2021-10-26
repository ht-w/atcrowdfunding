package io.hongting.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author hongting
 * @create 2021 10 20 1:13 PM
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/agree/protocol/page.html").setViewName("start");
        registry.addViewController("/launch/page.html").setViewName("launch");
        registry.addViewController("/info/page.html").setViewName("return");
        registry.addViewController("/confirm/page.html").setViewName("confirm");
        registry.addViewController("/success/page.html").setViewName("success");

    }
}

