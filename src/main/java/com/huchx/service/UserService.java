package com.huchx.service;

import com.huchx.dao.UserDao;
import com.huchx.entity.MUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserService {
    @Autowired
    UserDao userDao;
    public MUserEntity findUserById(String id){
        return  userDao.findUserById(Long.valueOf(id));
    }
}
