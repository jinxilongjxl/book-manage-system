package com.ibm.ssnb.dao;

import com.ibm.ssnb.entity.Config;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ConfigDao extends JpaRepository<Config, Integer> {

    @Query(value = "select * from t_config where id = ?1", nativeQuery = true)
    Config findId(Integer id);

}
