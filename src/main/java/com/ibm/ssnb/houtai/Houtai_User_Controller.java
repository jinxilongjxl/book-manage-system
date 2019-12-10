package com.ibm.ssnb.houtai;

import com.ibm.ssnb.dao.UserDao;
import com.ibm.ssnb.entity.Role;
import com.ibm.ssnb.service.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/houtai")
public class Houtai_User_Controller {

    @Resource
    private UserDao userDao;

    @Resource
    private RoleService roleService;


    @RequestMapping("/manage")
    public ModelAndView mananage() throws  Exception{

        ModelAndView mav = new ModelAndView();
        mav.addObject("pageTitle", "用户管理");
        mav.addObject("title", "用户管理");
        mav.setViewName("/admin/page/user/user_manage");
        return mav;
    }

    @RequestMapping("/add")
    public ModelAndView add() throws Exception {
        ModelAndView mav = new ModelAndView();
        Map<String, Object> map = null;
        List<Role> roleList = roleService.list(map, 0 , 1000);
        mav.addObject("roleList", roleList);
        mav.addObject("flag", true);
        mav.addObject("btn_text", "添加");
        mav.addObject("save_url", "/admin/user/add");
        mav.setViewName("/admin/page/user/add_update");
        return mav;
    }

}
