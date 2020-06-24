package com.huchx.config;

import com.huchx.security.interceptors.ApiRuleInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 请求地址拦截配置
 */
@Configuration
public class WebConfig  implements WebMvcConfigurer {

    @Bean
    public ApiRuleInterceptor apiRuleInterceptor(){return new ApiRuleInterceptor();}

    /**
     * 添加拦截规则，此拦截是为了验证头部信息是否完整，签名是否匹配(如果有签名)
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(apiRuleInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/**/login")
                .excludePathPatterns("/static/**");
    }
}
