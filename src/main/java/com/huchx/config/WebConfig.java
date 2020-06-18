package com.huchx.config;

import com.huchx.security.interceptors.ApiRuleInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig  implements WebMvcConfigurer {

    @Bean
    public ApiRuleInterceptor apiRuleInterceptor(){return new ApiRuleInterceptor();}
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(apiRuleInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/**/login")
                .excludePathPatterns("/static/**");
    }
}
