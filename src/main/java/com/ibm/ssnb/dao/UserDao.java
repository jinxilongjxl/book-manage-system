package com.ibm.ssnb.dao;

import com.ibm.ssnb.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer> {

    public User findByName(String name);

}
