package com.ibm.ssnb.service;

import com.ibm.ssnb.entity.Menu;

import java.util.List;
import java.util.Map;

public interface MenuService {

    Integer update(Menu menu);

    List<Menu> list(Map<String, Object> map, Integer page, Integer pageSize);

    Long getTotal(Map<String, Object> map);


}
