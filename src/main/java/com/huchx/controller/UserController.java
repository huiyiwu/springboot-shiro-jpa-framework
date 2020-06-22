package com.huchx.controller;

import com.huchx.service.UserService;
import com.huchx.utils.ResponseUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @RequestMapping("/userInfo")
    public Map userInfo(String id){
        if (StringUtils.isEmpty(id)){
            return new ResponseUtils().error("参数id为空");
        }
        return new ResponseUtils().ok(userService.findUserById(id));
    }
}
