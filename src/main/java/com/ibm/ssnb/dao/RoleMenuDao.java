package com.ibm.ssnb.dao;

import com.ibm.ssnb.entity.RoleMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RoleMenuDao extends JpaRepository<RoleMenu, Integer> , JpaSpecificationExecutor<RoleMenu> {
}
