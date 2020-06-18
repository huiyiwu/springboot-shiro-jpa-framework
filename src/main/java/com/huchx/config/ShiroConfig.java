package com.huchx.config;

import com.huchx.security.shiro.ShiroFilter;
import com.huchx.security.shiro.ShiroRealm;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public ShiroRealm shiroRealm(){
        return new ShiroRealm();
    }

    @Bean
    public SecurityManager securityManager(){
        DefaultSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(shiroRealm());
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String,String> shiroFilterMap = new LinkedHashMap<>();
        shiroFilterMap.put("/login","anon");
        shiroFilterMap.put("/**","myFilter");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(shiroFilterMap);

        ShiroFilter filter = new ShiroFilter();
        Map<String, Filter> filterMap = new LinkedHashMap<>();
        filterMap.put("myFilter",filter);
        shiroFilterFactoryBean.setFilters(filterMap);
        return shiroFilterFactoryBean;
    }
}
