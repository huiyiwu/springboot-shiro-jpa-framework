package com.huchx.security.interceptors;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.huchx.exception.ParameterMissException;
import com.huchx.security.TokenUtil;
import com.huchx.utils.ResponseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 请求拦截器
 */
public class ApiRuleInterceptor extends HandlerInterceptorAdapter {
    private Logger logger = LoggerFactory.getLogger(ApiRuleInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        try {
            TokenUtil.checkSign(request);
            return true;
        } catch (ParameterMissException e) {
            e.printStackTrace();
            buildErrorResponse(response,e.getMessage());
            return false;
        }
    }

    private void buildErrorResponse(HttpServletResponse response, String message) {
        try {
       Map<String,Object> errResponse =   new ResponseUtils().error(message);
       response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
       response.getWriter().print(JSON.toJSONString(errResponse, SerializerFeature.MapSortField));
        } catch (IOException e) {
            e.printStackTrace();
            logger.debug("build error response");
        }
    }
}
