package com.huchx.dao;

import com.huchx.entity.MUserEntity;
import com.huchx.repository.MyJpaRepository;


public interface ShiroDao extends MyJpaRepository<MUserEntity,Long> {

    MUserEntity findMUserById(Long id);
}
