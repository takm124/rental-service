package com.gamsung;

import com.gamsung.interceptor.AdminCheckInterceptor;
import com.gamsung.interceptor.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/", "/login", "/logout", "/css/**", "/*.ico", "/error", "/js/**", "/img/**");

        registry.addInterceptor(new AdminCheckInterceptor())
                .order(2)
                .addPathPatterns("/admin/**");

    }
}
