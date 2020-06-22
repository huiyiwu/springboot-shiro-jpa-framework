package com.huchx.dao;

import com.huchx.entity.MUserEntity;
import com.huchx.respository.MyJpaRepository;

import javax.persistence.Id;

public interface ShiroDao extends MyJpaRepository<MUserEntity,Long> {

    MUserEntity findMUserById(Long id);
}
