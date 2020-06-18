package com.huchx.controller;

import com.huchx.utils.ResponseUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @RequestMapping("/userInfo")
    public Map hello(){
        return new ResponseUtils().ok("userInfo");
    }
}
