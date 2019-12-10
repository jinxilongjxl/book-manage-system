package com.ibm.ssnb.service;

import com.ibm.ssnb.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    public Integer update(User user);

    public List<User> list(Map<String, Object> map, Integer page, Integer pageSize);

    public Long getTotal(Map<String, Object> map);


}
