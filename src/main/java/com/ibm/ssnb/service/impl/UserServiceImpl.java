package com.ibm.ssnb.service.impl;

import com.ibm.ssnb.dao.UserDao;
import com.ibm.ssnb.entity.Role;
import com.ibm.ssnb.entity.User;
import com.ibm.ssnb.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public Integer update(User user) {
        User origin = userDao.findId(user.getId());
        user = replace(user, origin);
        userDao.save(user);
        return 1;
    }

    private User replace(User current, User origin) {
        if (current.getName() == null) {
            current.setName(origin.getName());
        }

        if (current.getPwd() == null) {
            current.setPwd(origin.getPwd());
        }

        if (current.getTrueName() == null) {
            current.setTrueName(origin.getTrueName());
        }

        if (current.getRemark() == null) {
            current.setRemark(origin.getRemark());
        }

        if (current.getOrderNo() == null) {
            current.setOrderNo(origin.getOrderNo());
        }

        if (current.getCreateDateTime() == null) {
            current.setCreateDateTime(origin.getCreateDateTime());
        }

        if (current.getRole() == null) {
            current.setRole(origin.getRole());
        }

        return current;

    }

    @Override
    public List<User> list(Map<String, Object> map, Integer page, Integer pageSize) {

        Pageable pageable = PageRequest.of(0, pageSize, Sort.Direction.ASC, "orderNo");
        Page<User> list = userDao.findAll(pageable);
        return list.getContent();
    }

    @Override
    public Long getTotal(Map<String, Object> map) {

        return userDao.count();
    }
}
