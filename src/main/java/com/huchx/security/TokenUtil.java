package com.huchx.security;

import com.alibaba.fastjson.JSONObject;
import com.huchx.ApiContstants;
import com.huchx.exception.ParameterMissException;
import com.huchx.exception.ParseObjectException;
import com.huchx.exception.TokenExistException;
import com.huchx.exception.TokenExpiredException;
import com.huchx.security.shiro.ShiroAuthToken;
import com.huchx.utils.AESUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class TokenUtil {
    private static Logger logger = LoggerFactory.getLogger(ApiContstants.API_ERR_NAME);

    /**
     * 验证头部字段信息及签名有效性(如果有签名)
     * @param request
     * @throws ParameterMissException
     */
    public static void checkSign(HttpServletRequest request) throws ParameterMissException {
        checkHeader(request);
        //目前只是检查了是否有头部信息，如果有签名，可在此处处理
        //TODO 处理签名信息
        //
        logger.debug("aaa");
    }

    private static void checkHeader(HttpServletRequest request) throws ParameterMissException {
        if (ApiContstants.HEADERS!=null&&ApiContstants.HEADERS.size()>0){
            for (Object name:ApiContstants.HEADERS
                 ) {
                if (request.getHeader(String.valueOf(name))==null){
                    throw new ParameterMissException("缺少头部参数"+name);
                }
            }
        }
    }

    /**
     *
     * @param token
     * @param shiroAuthToken
     */
    public static boolean  checkToken(String token, ShiroAuthToken shiroAuthToken) throws TokenExpiredException, TokenExistException, ParseObjectException {
        // TODO: 验证token有效性
        if (StringUtils.isEmpty(token)||shiroAuthToken==null){
            throw new TokenExistException("用户缺少token信息");
        }
        if (shiroAuthToken==null){
            shiroAuthToken = TokenUtil.tokenToUser(token);
        }
        if (System.currentTimeMillis()>shiroAuthToken.getValidTimeMillons()){
            throw new TokenExpiredException("token已过期");
        }
        return true;
    }

    public static ShiroAuthToken parseToken(HttpServletRequest request) throws ParameterMissException, ParseObjectException, TokenExpiredException {
        String token = request.getHeader(ApiContstants.TOKEN_KEY);
        if (StringUtils.isEmpty(token)){
            throw new ParameterMissException("缺少头部信息token");
        }
       ShiroAuthToken shiroAuthToken = tokenToUser(token);
        if (System.currentTimeMillis()>shiroAuthToken.getValidTimeMillons()){
            throw new TokenExpiredException("token已失效,请重新登陆");
        }
        return  shiroAuthToken;
    }

    private static ShiroAuthToken tokenToUser(String token) throws ParseObjectException {
String tokenJson = null;
        try {
            tokenJson = AESUtil.decrypt(token);
            return JSONObject.parseObject(tokenJson,ShiroAuthToken.class);
        } catch (Exception e) {
            e.printStackTrace();
            logger.debug("token parse to user error:"+token,e);
            throw new ParseObjectException("token转为验证用户信息失败");
        }
    }
}
