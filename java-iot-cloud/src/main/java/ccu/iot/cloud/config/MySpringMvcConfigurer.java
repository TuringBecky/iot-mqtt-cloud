package ccu.iot.cloud.config;


import ccu.iot.cloud.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/***
 * springmvc组件配置类
 */

@Configuration
public class MySpringMvcConfigurer implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(loginInterceptor);
        //需要拦截的请求
        interceptorRegistration.addPathPatterns("/**");
        //不需要拦截的请求
        // interceptorRegistration.excludePathPatterns("/", "/index.html", "/login", "/css/**", "/img/**", "/js/**");
        interceptorRegistration.excludePathPatterns("/user/**","/user/signUp");
    }


}
