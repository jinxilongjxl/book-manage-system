package com.ibm.ssnb.dao;

import com.ibm.ssnb.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RoleDao extends JpaRepository<Role, Integer>, JpaSpecificationExecutor<Role> {
}
