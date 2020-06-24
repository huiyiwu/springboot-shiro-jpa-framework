package com.huchx.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.Map;

/**
 * 自定义Repository，实现公共方法，暂未成功，先不用看
 * @param <T>
 * @param <ID>
 */
@NoRepositoryBean
public interface MyJpaRepository<T,ID extends Serializable> extends JpaRepository<T,ID>, JpaSpecificationExecutor<T> {
    Page<Map<String,Object>> findPageByNativeQuery(String name, Pageable pageable,Map<String,Object> prams);

}
