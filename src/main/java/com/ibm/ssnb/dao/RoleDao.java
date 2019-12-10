package com.ibm.ssnb.dao;

import com.ibm.ssnb.entity.Role;
import com.ibm.ssnb.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface RoleDao extends JpaRepository<Role, Integer>, JpaSpecificationExecutor<Role> {

    @Query(value = "select * from t_a_role where id = ?1", nativeQuery = true)
    public Role findId(Integer id);

}
