package com.ibm.ssnb.service.impl;

import com.ibm.ssnb.dao.ConfigDao;
import com.ibm.ssnb.entity.Config;
import com.ibm.ssnb.entity.User;
import com.ibm.ssnb.service.ConfigService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("configService")
public class ConfigServiceImpl implements ConfigService {

    @Resource
    private ConfigDao configDao;


    @Override
    public Config findById(Integer id) {
        return configDao.findId(id);
    }
}
