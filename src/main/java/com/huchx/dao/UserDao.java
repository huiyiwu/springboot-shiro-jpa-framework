package com.huchx.dao;

import com.huchx.annotation.CustomQueries;
import com.huchx.annotation.CustomQuery;
import com.huchx.entity.MUserEntity;
import com.huchx.respository.MyJpaRepository;

public interface UserDao extends MyJpaRepository<MUserEntity,Long> {

    MUserEntity findUserById(Long id);
}
