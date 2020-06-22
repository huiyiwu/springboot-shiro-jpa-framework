package com.huchx;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ApiContstants {
    //logging
    public static final String API_ERR_NAME = "api_error";

    //response
    public static final String RESULT_CODE_OK = "200";
    public static final String RESULT_CODE_ERROR = "500";
    public static final String RESULT_CODE_OK_MESSAGE = "操作成功";
    public static final String RESULT_CODE_ERROR_MESSAGE = "操作失败";

    //session
    public static final int SESSION_TIMEOUT_SECONDS = 2 * 60 * 60*1000;

    //header
    public static final String TOKEN_KEY = "token";
    //sign
    public static final String AES_KEY = "de@5H98H*^hCfX%dfC$r3H4&";// AES加密要求key必须要128个比特位（这里需要长度为16，否则会报错）
    public static final String PWD_SALT = "de@huChXH4&";

    public  static ArrayList HEADERS =new ArrayList();

    static {
        HEADERS.add("userId");
        HEADERS.add("token");
    }
}
