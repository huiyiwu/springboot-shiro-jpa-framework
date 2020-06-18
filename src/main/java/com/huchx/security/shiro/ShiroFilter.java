package com.huchx.security.shiro;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.huchx.security.TokenUtil;
import com.huchx.utils.ResponseUtils;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class ShiroFilter extends AccessControlFilter {
    Logger logger = LoggerFactory.getLogger(ShiroFilter.class);
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) {
        try {
            ShiroAuthToken token = TokenUtil.parseToken((HttpServletRequest) servletRequest);
            getSubject(servletRequest,servletResponse).login(token);
        }catch (Exception e){
            logger.debug("shirofilter 拦截失败",e);
            buildErrorResponse((HttpServletResponse) servletResponse,e.getMessage());
            return false;
        }
        return true;
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
