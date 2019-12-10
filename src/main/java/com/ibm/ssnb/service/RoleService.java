package com.ibm.ssnb.service;

import com.ibm.ssnb.entity.Role;

import java.util.List;
import java.util.Map;

public interface RoleService {


    public Integer update(Role role);

    public List<Role> list(Map<String, Object> map, int page, int pageSize);

    public Long getTotal(Map<String, Object> map);


}
