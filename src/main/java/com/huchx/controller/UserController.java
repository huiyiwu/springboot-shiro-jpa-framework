package com.huchx.controller;

import com.alibaba.fastjson.JSONObject;
import com.huchx.ApiContstants;
import com.huchx.entity.MUserEntity;
import com.huchx.security.TokenUtil;
import com.huchx.security.shiro.ShiroAuthToken;
import com.huchx.service.UserService;
import com.huchx.utils.AESUtil;
import com.huchx.utils.PageUtil;
import com.huchx.utils.PasswordHelper;
import com.huchx.utils.ResponseUtils;
import com.huchx.vo.AddUserVo;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    /**
     * 用户信息
     * @param id
     * @return
     */
    @RequestMapping("/userInfo")
    public Map userInfo(String id){
        if (StringUtils.isEmpty(id)){
            return new ResponseUtils().error("参数id为空");
        }
        return new ResponseUtils().ok(userService.findUserById(id));
    }

    /**
     * 分页查询，参数是写死的
     * @return
     */
    @RequestMapping("list")
    public Map<String,Object> userList(){
        return new ResponseUtils().ok(PageUtil.parseToModel(userService.findUserByPage()));
    }

    /**
     * 添加用户，类似于注册
     * @param addUserVo
     * @return
     *
     * @RequestBody post请求，参数放置body中,使用对象接收
     * @RequestParam get请求，参数拼接在url中，使用字段接口
     * 不加，get请求 和post 参数放置form-data中，使用字段接收
     * @PathVariable 参数占位符
     *
     */
    @RequestMapping("addUser")
    public Map<String,Object> addUser(@RequestBody AddUserVo addUserVo){
        if (StringUtils.isEmpty(addUserVo.getName())){
            return new ResponseUtils().error("参数name为空");
        }
        if (StringUtils.isEmpty(addUserVo.getPassword())){
            return new ResponseUtils().error("参数password为空");
        }
        MUserEntity mUserEntity = new MUserEntity();
        mUserEntity.setName(addUserVo.getName());
        mUserEntity.setPassword(addUserVo.getPassword());
        mUserEntity.setSalt(ApiContstants.PWD_SALT);
        PasswordHelper.encryptPassword(mUserEntity);
        try {
            mUserEntity.setToken(getToken(mUserEntity.getId()));
            userService.InsertUserByName(mUserEntity);
            return new ResponseUtils().ok("保存成功");
        }catch (Exception e){
            return new ResponseUtils().error("保存失败");
        }
    }

    /**
     * 登陆后返回token，可以放置数据库
     * @param id
     * @return
     * @throws Exception
     */
    private String getToken(long id) throws Exception {
        ShiroAuthToken shiroAuthToken = new ShiroAuthToken(id);
        String token = AESUtil.encrypt(JSONObject.toJSONString(shiroAuthToken));
        return token;
    }
}
