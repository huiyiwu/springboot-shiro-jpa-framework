package com.huchx.controller;

import com.huchx.utils.ResponseUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class LoginController {

    @RequestMapping("login")
    public Object login(){
        return new ResponseUtils().ok("login");
    }
}
