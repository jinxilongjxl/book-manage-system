package com.ibm.ssnb.dao;

import com.ibm.ssnb.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserDao extends JpaRepository<User, Integer> {

    public User findByName(String name);

    @Query(value = "select * from t_a_user where id =?!", nativeQuery = true)
    public User findId(Integer id);



}
