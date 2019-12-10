package com.ibm.ssnb.admin;


import com.ibm.ssnb.dao.UserDao;
import com.ibm.ssnb.entity.User;
import com.ibm.ssnb.service.UserService;
import com.ibm.ssnb.util.CryptographyUtil;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
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
@RequestMapping("/admin/user")
public class Admin_User_Controller {

    @Resource
    private UserDao userDao;

    @Resource
    private UserService userService;

    @ResponseBody
    @RequestMapping("/add")
    public JSONObject add(@Valid User user, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) throws Exception{

        JSONObject result = new JSONObject();

        if (bindingResult.hasErrors()) {
            result.put("success", false);
            result.put("msg", bindingResult.getFieldError().getDefaultMessage());
            return result;
        } else {
            user.setCreateDateTime(new Date());
            user.setPwd(CryptographyUtil.md5(user.getPwd(), "java"));
            userDao.save(user);
            result.put("success", false);
            result.put("msg", "添加成功");
            return result;
        }

    }

    @ResponseBody
    @RequestMapping("/update")
    public JSONObject update(@Valid User user, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) {

        JSONObject result = new JSONObject();

        if (bindingResult.hasErrors()) {
            result.put("success", false);
            result.put("msg", bindingResult.getFieldError().getDefaultMessage());
            return result;
        } else {

            String webPath = request.getServletContext().getRealPath("");
            user.setCreateDateTime(new Date());
            userService.update(user);
            result.put("success", true);
            result.put("msg", "修改成功");
            return result;
        }


    }

    @ResponseBody
    @RequestMapping("/set_new_pwd")
    public JSONObject set_new_pwd(User user, HttpServletRequest request)  throws  Exception{

        JSONObject result = new JSONObject();

        user.setUpdateDateTime(new Date());
        if (StringUtils.isNotEmpty(user.getPwd())) {
            user.setPwd(CryptographyUtil.md5(user.getPwd(), "java"));
        }

        String webPath = request.getServletContext().getRealPath("");
        userService.update(user);
        result.put("success", true);
        result.put("msg", "修改成功");
        return result;

    }

    @ResponseBody
    @RequestMapping("/list")
    public Map<String, Object> list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "limit", required = false) Integer limit, HttpServletRequest request, HttpServletResponse response) {

        Map<String, Object> map = new HashMap<>();
        List<User> userList = userService.list(map, page -1 , limit);
        long total = userService.getTotal(map);
        map.put("data", userList);
        map.put("count", total);
        map.put("code", 0);
        map.put("msg", "");
        return map;


    }

    @ResponseBody
    @RequestMapping("/delete")
    public JSONObject delete(@RequestParam(value = "ids", required = false) String ids, HttpServletResponse response)  throws  Exception{

        String[] idsStr = ids.split(",");
        JSONObject result = new JSONObject();

        for (int i = 0; i < idsStr.length; i++) {
            userDao.deleteById(Integer.parseInt(idsStr[i]));
        }

        result.put("success", true);
        return result;
    }

}
