package com.huchx.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MyJpaRepository<T,ID> extends JpaRepository {
          //CurdRepository
        
    //CurdRepository end
}
