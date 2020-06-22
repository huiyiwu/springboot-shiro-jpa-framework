package com.huchx.respository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Map;
import java.util.Optional;

@NoRepositoryBean
public interface MyJpaRepository<T,ID> extends JpaRepository<T,ID>, JpaSpecificationExecutor<T> {
}
