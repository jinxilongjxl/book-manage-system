package com.ibm.ssnb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class IndexController {

    @Resource
    private ServletContext servletContext;

    /**
     *# 请求首页
     */
    @RequestMapping("/")
    public String  index_1(HttpServletResponse res, HttpServletRequest req) throws Exception {
        return "redirect:/login";
    }

    /**
     *   #请求首页  /index
     */
    @RequestMapping("/index")
    public String index(HttpServletResponse  res,HttpServletRequest req) throws Exception {
        return "redirect:/login";
    }

    @RequestMapping("/login")
    public ModelAndView login(HttpServletRequest req, HttpServletResponse res) {

        ModelAndView mav = new ModelAndView();
        mav.setViewName("/pc/login/login");
        return mav;
    }

    /**
     * # 后台主页
     */
    @RequestMapping("/admin/main")
    public String admin_main(){
        return "/admin/main";
    }

}
