package com.ibm.ssnb.admin;

import com.ibm.ssnb.dao.RoleDao;
import com.ibm.ssnb.entity.Role;
import com.ibm.ssnb.service.RoleService;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/role")
public class Admin_Role_Controller {

    @Resource
    private RoleService roleService;

    @Resource
    private RoleDao roleDao;


    @RequestMapping("/add")
    @ResponseBody
    public JSONObject add(@Valid Role role, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) {
        JSONObject result = new JSONObject();
        role.setCreateDateTime(new Date());

        if (bindingResult.hasErrors()) {
            result.put("success", false);
            result.put("msg", bindingResult.getFieldError().getDefaultMessage());
            return result;
        } else {
            roleDao.save(role);
            result.put("success", true);
            return result;
        }
    }

    @ResponseBody
    @RequestMapping("/update")
    public JSONObject update(@Valid Role role, BindingResult bindingResult)  throws  Exception{

        JSONObject result = new JSONObject();

        if (bindingResult.hasErrors()) {
            result.put("success", false);
            result.put("msg", bindingResult.getFieldError().getDefaultMessage());
            return result;
        } else {
            role.setUpdateDateTime(new Date());
            roleService.update(role);
            result.put("success", true);
            result.put("msg", "修改成功");
            return result;
        }

    }

    @ResponseBody
    @RequestMapping("/list")
    public Map<String, Object> list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "limit", required = false) Integer limit, HttpServletResponse response, HttpServletRequest request)  throws Exception{

        Map<String, Object> map = new HashMap<String, Object>();
        List<Role> list = roleService.list(map, page - 1, limit);
        long total = roleService.getTotal(map);
        map.put("data", list);
        map.put("count", total);
        map.put("code", 0);
        map.put("msg", "");
        return map;

    }


    @ResponseBody
    @RequestMapping("/delete")
    public JSONObject delete(@RequestParam(value = "ids", required = false) String ids, HttpServletResponse response) {

        String[] idsStr = ids.split(",");

        JSONObject result = new JSONObject();
        for (int i = 0; i < idsStr.length; i++) {
            try {
                roleDao.deleteById(Integer.parseInt(idsStr[i]));
            } catch (Exception e) {
                e.printStackTrace();
                result.put("success", false);
                result.put("msg", "有用户正在使用此角色");
                return result;
            }
        }
        result.put("success", true);
        return result;
    }

}
