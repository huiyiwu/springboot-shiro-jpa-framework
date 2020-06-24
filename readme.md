###spring-boot+shiro+spring-data-jpa API开发使用基础框架

#### 一、  集成spring-boot
 1、  添加spring-boot依赖
 ```xml
    <parent>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-parent</artifactId>
       <version>2.0.0.RELEASE</version>
     </parent>
   ```
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```
单元测试依赖(如果需要)
```xml
   <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-test</artifactId>
      <scope>test</scope>
    </dependency>
```
添加热部署及maven插件,`仅在开发环境有效`
```xml
 <dependency>
     <groupId>org.springframework.boot</groupId>
     <artifactId>spring-boot-devtools</artifactId>
     <optional>true</optional>
 </dependency>

 <plugin>
     <groupId>org.springframework.boot</groupId>
     <artifactId>spring-boot-maven-plugin</artifactId>
     <configuration>
         <fork>true</fork>
     </configuration>
 </plugin>
```
2、 修改启动类,添加`@SpringBootApplication`注解，使用`SpringApplication`启动
```java
    @SpringBootApplication
    public class Application
    {
        public static void main( String[] args )
        {
            SpringApplication.run(Application.class,args);
        }
    }
```
#### 二、 集成shiro
 1. 添加依赖
 ```xml
  <dependency>
      <groupId>org.apache.shiro</groupId>
      <artifactId>shiro-spring</artifactId>
      <version>1.4.0</version>
    </dependency>
 ```
 
 2. 添加自动配置
 ```java

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
 1. shiro 安全验证插件
 2. 关于shiro的配置
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

 ```

 3. 自定义身份验证，授权Realm类
 ```java
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 1. 身份验证、权限角色验证
 */
public class ShiroRealm extends AuthorizingRealm {
    Logger logger = LoggerFactory.getLogger(ShiroRealm.class);

    @Autowired
    ShiroService shiroService;
    /**
     * 用户授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        return info;
    }

    /**
     * 验证用户有效性
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        ShiroAuthToken shiroAuthToken = (ShiroAuthToken) authenticationToken;
        MUserEntity mUserEntity = shiroService.findUserById(Long.valueOf(shiroAuthToken.getUserId()));
        if (mUserEntity == null) {
            throw new UnknownAccountException("用户不存在");
        }
        try {
            TokenUtil.checkToken(mUserEntity.getToken(),shiroAuthToken);
        }catch (TokenExistException e){
         throw new UnknownAccountException(e.getMessage());
        }catch (TokenExpiredException e){
            throw new UnknownAccountException(e.getMessage());
        }catch (Exception e){
            throw new UnknownAccountException(e.getMessage());
        }
        return new SimpleAuthenticationInfo(shiroAuthToken,mUserEntity.getPassword(), ByteSource.Util.bytes(mUserEntity.getPassword()),getName());
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof ShiroAuthToken;
    }
```
#### 三、集成spring-data-jpa
 1. 添加依赖
	```java
	<!--      Jpa依赖-->
	      <dependency>
	          <groupId>org.springframework.boot</groupId>
	          <artifactId>spring-boot-starter-data-jpa</artifactId>
	      </dependency>
	      <!--数据库连接驱动-->
	      <dependency>
	          <groupId>mysql</groupId>
	          <artifactId>mysql-connector-java</artifactId>
	          <version>8.0.15</version>
	      </dependency>
	```
 2. 配置数据源
 ```yml
 ## database
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/framework?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8&useSSL=true
    username: root
    password: 123456
    driver-class-name: com.mysql.cj.jdbc.Driver
  jpa:
    show-sql: true
    hibernate:
      ddl-auto: update
```

 3. 使用Dao操作
 ```java
//dao类
public interface UserDao extends MyJpaRepository<MUserEntity,Long> {

    MUserEntity findUserById(Long id);
}
//service类
@Service
public class UserService {
    @Autowired
    UserDao userDao;
    public MUserEntity findUserById(String id){
        return  userDao.findUserById(Long.valueOf(id));
    }
}
```
#### 四、其他依赖及功能说明
> 依赖
```xml
<!--    StringUtils工具类,引入字符串操作类-->
    <dependency>
      <groupId>org.apache.commons</groupId>
      <artifactId>commons-lang3</artifactId>
      <version>3.4</version>
    </dependency>
    <!--    Json转换-->
    <dependency>
      <groupId>com.alibaba</groupId>
      <artifactId>fastjson</artifactId>
      <version>1.2.58</version>
    </dependency>
```
