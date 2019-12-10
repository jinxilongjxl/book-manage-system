package com.ibm.ssnb.service.impl;

import com.ibm.ssnb.dao.MenuDao;
import com.ibm.ssnb.dao.RoleDao;
import com.ibm.ssnb.entity.Role;
import com.ibm.ssnb.service.RoleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("roleServiec")
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleDao roleDao;

    @Resource
    private MenuDao menuDao;

    @Override
    public Integer update(Role role) {
        Role origin = roleDao.findId(role.getId());
        role = replace(role, origin);
        roleDao.save(role);
        return 1;
    }

    private Role replace(Role curr, Role origin) {
        if (curr.getCreateDateTime() == null) {
            curr.setCreateDateTime(origin.getCreateDateTime());
        }

        if (curr.getName() == null) {
            curr.setName(origin.getName());
        }

        if (curr.getOrderNo() == null) {
            curr.setOrderNo(origin.getOrderNo());
        }

        if (curr.getUpdateDateTime() == null) {
            curr.setUpdateDateTime(origin.getUpdateDateTime());
        }

        return curr;
    }

    @Override
    public List<Role> list(Map<String, Object> map, int page, int pageSize) {
        // Pageable pageable = new PageRequest(0, 11, Sort.Direction.ASC, "orderNo");
        Pageable pageable = PageRequest.of(0, 11, Sort.Direction.ASC);
        Page<Role> list = roleDao.findAll(pageable);

        return list.getContent();
    }

    @Override
    public Long getTotal(Map<String, Object> map) {
        return roleDao.count();
    }


}
