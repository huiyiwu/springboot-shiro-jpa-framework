package com.huchx.service;

import com.huchx.dao.ShiroDao;
import com.huchx.entity.MUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShiroService {

    @Autowired
    ShiroDao shiroDao;

    public MUserEntity findUserById(Long id){
        return shiroDao.findMUserById(id);
    }
}
