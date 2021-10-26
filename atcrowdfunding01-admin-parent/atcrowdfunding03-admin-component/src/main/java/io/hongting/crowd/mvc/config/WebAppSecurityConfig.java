package io.hongting.crowd.mvc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.util.AntPathMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebAppSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private BCryptPasswordEncoder passwordEncoder;



    @Autowired
    private UserDetailsService userDetailsService;


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        String []permitUrls = {"/index.jsp","/bootstrap/**",
                "/crowd/**","/css/**","/fonts/**","/img/**",
                "/jquery/**","/layer/**","/script/**","/ztree/**","/admin/login/page.html"};

            http.authorizeRequests()
                .antMatchers(permitUrls)
                .permitAll()
					   // 必须具有经理的角色
                 .anyRequest()
                .authenticated();

            http.csrf()
                    .disable()
                    .formLogin()
                    .loginPage("/admin/login/page.html")
                    .usernameParameter("loginAcct")
                    .passwordParameter("userPwsd")
                    .loginProcessingUrl("/security/login.html")
                    .defaultSuccessUrl("/admin/main/page.html")
                    .and()
                    .logout()
                    .logoutUrl("/security/logout.html")
                    .logoutSuccessUrl("/admin/login/page.html");

            http.exceptionHandling().accessDeniedHandler(new AccessDeniedHandler() {
                @Override
                public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
                    httpServletRequest.setAttribute("exception", new Exception("抱歉，您没有权限访问该资源！"));
                    httpServletRequest.getRequestDispatcher("/WEB-INF/system-error.jsp").forward(httpServletRequest,httpServletResponse);
                }

            });


    }


//    @Override
//    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
//        builder
//                .inMemoryAuthentication()        // 开启在内存中进行身份验证（开发时暂用）
//                .withUser("tom")        		 // 设置用户名
//                .password("123456")              // 设置密码
//                .roles("ADMIN");                 // 设置权限
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }
}