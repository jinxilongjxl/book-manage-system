package com.ibm.ssnb.admin;

import com.ibm.ssnb.dao.MenuDao;
import com.ibm.ssnb.entity.Menu;
import com.ibm.ssnb.service.MenuService;
import net.sf.json.JSONObject;
import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/menu")
public class Admin_Menu_Controller {

    @Resource
    private MenuService menuService;

    @Resource
    private MenuDao menuDao;

    @ResponseBody
    @RequestMapping("/add")
    public JSONObject add(@Valid Menu menu, BindingResult bindingResult, HttpServletResponse response, HttpServletRequest request) {

        JSONObject result = new JSONObject();

        if (bindingResult.hasErrors()) {
            result.put("success", false);
            result.put("msg", bindingResult.getFieldError().getDefaultMessage());
            return result;
        } else {
            menuDao.save(menu);
            result.put("success", true);
            result.put("msg", "添加成功");
            return result;
        }
    }

    @RequestMapping("/update")
    @ResponseBody
    public JSONObject update(Menu menu) throws  Exception{

        JSONObject result = new JSONObject();
        menuService.update(menu);
        result.put("success,", true);
        result.put("msg", "修改成功");
        return result;
    }

    @ResponseBody
    @RequestMapping("/list")
    public Map<String, Object> list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "limit", required = false) Integer limit, @RequestParam(value = "pId", required = false) String pId, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();

        if (StringUtils.isEmpty(pId)) {
            map.put("pId", pId);
        }

        List<Menu> list = menuService.list(map, page-1, limit);
        Long total = menuService.getTotal(map);
        map.clear();
        map.put("data", list);
        map.put("count", total);
        map.put("code", 0);
        map.put("msg", "");
        return map;
    }

    @ResponseBody
    @RequestMapping("/delete")
    public JSONObject delete(@RequestParam(value = "ids", required = false) String ids, HttpServletResponse response) {

        JSONObject result = new JSONObject();

        String[] idsStr = ids.split(",");

        Map<String, Object> map = new HashMap<>();
        for (int i = 0; i < idsStr.length; i++) {
            try {
                map.put("pId", Integer.parseInt(idsStr[i]));
                // 看看下面有没有菜单，一同删除
                List<Menu> menuList = menuService.list(map, 0, 100);
                for (Menu menu : menuList) {
                    menuDao.deleteById(menu.getId());

                }

                menuDao.deleteById(Integer.parseInt(idsStr[i]));

            } catch (Exception e) {
                e.printStackTrace();
                result.put("success" , false);
                result.put("msg", "有角色正在使用这些菜单");
                return result;
            }
        }
        result.put("success", true);
        return result;
    }

}


