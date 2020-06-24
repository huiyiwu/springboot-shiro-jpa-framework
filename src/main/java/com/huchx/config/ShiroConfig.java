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
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro 安全验证插件
 * 关于shiro的配置
 */
@Configuration
public class ShiroConfig {
    /**
     * 设置RealmBean
     * @return
     */
    @Bean
    public ShiroRealm shiroRealm(){
        return new ShiroRealm();
    }

    /**
     * 设置安全管理类
     * @return
     */
    @Bean
    public SecurityManager securityManager(){
        DefaultSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(shiroRealm());
        return securityManager;
    }

    /**
     * 设置拦截规则
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //配置安全管理类
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String,String> shiroFilterMap = new LinkedHashMap<>();
        shiroFilterMap.put("/login","anon");
        shiroFilterMap.put("/**","myFilter");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(shiroFilterMap);

        //自定义拦截规则
        ShiroFilter filter = new ShiroFilter();
        Map<String, Filter> filterMap = new LinkedHashMap<>();
        filterMap.put("myFilter",filter);
        shiroFilterFactoryBean.setFilters(filterMap);
        return shiroFilterFactoryBean;
    }
}
