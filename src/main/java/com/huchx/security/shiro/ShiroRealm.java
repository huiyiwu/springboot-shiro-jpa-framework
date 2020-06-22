package com.huchx.security.shiro;

import com.huchx.entity.MUserEntity;
import com.huchx.exception.TokenExistException;
import com.huchx.exception.TokenExpiredException;
import com.huchx.security.TokenUtil;
import com.huchx.service.ShiroService;
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

    /**
     * 设定Password校验的Hash算法与迭代次数.
     */
    @PostConstruct
    public void initCredentialsMatcher() {
        CredentialsMatcher matcher = (token, info) -> true;
        setCredentialsMatcher(matcher);
    }
}
