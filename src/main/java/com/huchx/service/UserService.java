package com.huchx.service;

import com.huchx.dao.UserDao;
import com.huchx.entity.MUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    @Autowired
    UserDao userDao;
    public MUserEntity findUserById(String id){
        return  userDao.findUserById(Long.valueOf(id));
    }

    public Page<MUserEntity> findUserByPage() {
        Pageable pageable = PageRequest.of(0,3);
        return userDao.findAll(pageable);
    }

    @Transactional
    @Modifying
    public MUserEntity InsertUserByName(MUserEntity userEntity) {
        return userDao.save(userEntity);
    }
}
