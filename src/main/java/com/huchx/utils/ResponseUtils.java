package com.huchx.utils;

import com.huchx.ApiContstants;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * response 返回数据对象工具类
 * @param <T>
 */
public class ResponseUtils<T> {
    private static final String CODE = "code";
    private static final String MESSAGE = "message";
    private static final String RESULT = "result";


    public ResponseUtils(){}

    public  Map<String, Object> ok(T result){
        return ok(result,null);
    }

    public  Map<String, Object> ok(T result, String message){
        Map<String,Object> resultMap = new TreeMap<>();
        resultMap.put(RESULT,result);
        resultMap.put(MESSAGE,StringUtils.isBlank(message)? ApiContstants.RESULT_CODE_OK_MESSAGE:message);
        resultMap.put(CODE,ApiContstants.RESULT_CODE_OK);
        return resultMap;
    }

    public  Map<String, Object> error(String message){
        return error(message,ApiContstants.RESULT_CODE_ERROR);
    }
    public  Map<String, Object> error(String message,String code){
        return error(null,message,code);
    }

    public Map<String, Object> error(T result, String message,String code ){
        Map<String,Object> resultMap = new TreeMap<>();
        resultMap.put(RESULT, result==null?"":result);
        resultMap.put(MESSAGE,  StringUtils.isBlank(message)?ApiContstants.RESULT_CODE_ERROR_MESSAGE:message);
        resultMap.put(CODE,code);
        return resultMap;
    }
}
