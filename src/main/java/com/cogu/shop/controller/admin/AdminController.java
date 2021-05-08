package com.cogu.shop.controller.admin;

import com.cogu.shop.controller.entity.RestResult;
import com.cogu.shop.controller.entity.Tree;
import com.cogu.shop.entity.vo.Admin;
import com.cogu.shop.entity.vo.Role;
import com.cogu.shop.entity.vo.Turl;
import com.cogu.shop.service.AdminService;
import com.cogu.shop.service.RoleService;
import com.cogu.shop.tools.PageBean;
import com.cogu.shop.tools.UrlCompare;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Cogu
 * @Date: 2018/6/28 16:56
 * @Description:
 */
@CrossOrigin("*")
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return new ModelAndView("admin/login");
    }

    @PostMapping("/check")
    @ResponseBody
    public ModelMap urlCheck(@SessionAttribute("url") List<String> url,
                             @RequestParam("src") String src) {
        ModelMap modelMap = new ModelMap();
        if (UrlCompare.findUrl(url, src)) {
            modelMap.addAttribute("code", 200);
            return modelMap;
        } else {
            modelMap.addAttribute("code", 500);
            modelMap.addAttribute("message", "权限不够");
            return modelMap;
        }
    }

    @GetMapping("/role")
    public String initRole(@SessionAttribute("url") List<String> url) {
        if (UrlCompare.findUrl(url, "/admin/role")) {
            return "admin/role";
        } else {
            return "admin/index";
        }
    }

    @GetMapping("/admin")
    public String initAdmin(@SessionAttribute("url") List<String> url) {
        if (UrlCompare.findUrl(url, "/admin/admin")) {
            return "admin/admin";
        } else {
            return "admin/index";
        }
    }

    @GetMapping("/url")
    public String initUrl(@SessionAttribute("url") List<String> url) {
        if (UrlCompare.findUrl(url, "/admin/url")) {
            return "admin/url";
        } else {
            return "admin/index";
        }
    }

    @GetMapping("/category")
    public String initCategory(@SessionAttribute("url") List<String> url) {
        if (UrlCompare.findUrl(url, "/admin/category")) {
            return "admin/category";
        } else {
            return "admin/index";
        }
    }

    @GetMapping("/goods")
    public String initGoods(@SessionAttribute("url") List<String> url) {
        if (UrlCompare.findUrl(url, "/admin/goods")) {
            return "admin/goods";
        } else {
            return "admin/index";
        }
    }

    @GetMapping("/order")
    public String initOrder(@SessionAttribute("url") List<String> url) {
        if (UrlCompare.findUrl(url, "/admin/order")) {
            return "admin/order";
        } else {
            return "admin/index";
        }
    }

    @GetMapping("/log")
    public String initLog(@SessionAttribute("url") List<String> url) {
        if (UrlCompare.findUrl(url, "/admin/log")) {
            return "admin/log";
        } else {
            return "admin/index";
        }
    }


    @PostMapping("/get_parent")
    @ResponseBody
    public RestResult<List<Tree>> getAllRole() {
        List<Tree> roleList = roleService.findRole();
        if (roleList != null && roleList.size() > 0) {
            return new RestResult<>(200, roleList, "获取成功");
        } else {
            return new RestResult<>(201, null, "类别为空");
        }
    }

    @PostMapping("/getAll")
    @ResponseBody
    public ModelMap getAdmin(@RequestParam(value = "page", required = false) Integer page,
                             @RequestParam(value = "rows", required = false) Integer size) {
        ModelMap modelMap = new ModelMap();
        PageBean pageBean = new PageBean(page, size);
        List<Admin> adminList = adminService.findAdmin(pageBean.getPage(), pageBean.getPageSize());
        Integer total = adminService.findCount();
        if (adminList != null && adminList.size() > 0) {
            modelMap.addAttribute("rows", adminList);
            modelMap.addAttribute("total", total);
        }
        return modelMap;
    }

    @PostMapping("/add")
    @ResponseBody
    public RestResult<Admin> addAdmin(@ModelAttribute("Admin") Admin admin,
                                      @SessionAttribute("url") List<String> url,
                                      @RequestParam("rid") Integer... rid) {
        if (!UrlCompare.findUrl(url, "/admin/add")) {
            return new RestResult<>(403, null, "权限不够");
        }
        Integer result = adminService.saveAdmin(admin, rid);
        if (result == 1) {
            return new RestResult<>(200, admin, "添加成功");
        } else if (result == 2) {
            return new RestResult<>(500, null, "添加失败,账号重复");
        } else {
            return new RestResult<>(500, null, "添加失败");
        }
    }

    @PostMapping("/update")
    @ResponseBody
    public RestResult<Integer> updateAdmin(@ModelAttribute("Admin") Admin admin,
                                           @SessionAttribute("url") List<String> url,
                                           @SessionAttribute("admin") Admin admin2,
                                           HttpServletRequest request,
                                           @RequestParam("rid") Integer... rid) {
        if (!UrlCompare.findUrl(url, "/admin/update")) {
            return new RestResult<>(403, null, "权限不够");
        }
        Integer result = adminService.updateAdmin(admin, rid);
        if (result == 1) {
            if (admin.getAid() == admin2.getAid()) { //如果更新的是本人
                admin2 = adminService.findAdmin(admin.getUsername(), admin.getPassword());
                request.getSession().setAttribute("admin", admin2);
                List<String> urlList = new ArrayList<>();
                for (Role role : admin.getRoleList()) {
                    for (Turl turl : role.getTurlList()) {
                        urlList.add(turl.getFurl());
                    }
                }
                request.getSession().setAttribute("url", urlList);
            }
            return new RestResult<>(200, null, "修改成功");
        } else {
            return new RestResult<>(500, null, "修改失败");
        }
    }

    @PostMapping("/delete")
    @ResponseBody
    public RestResult<Integer> deleteAdmin(@RequestParam("aid") Integer aid,
                                           @SessionAttribute("url") List<String> url,
                                           @SessionAttribute("admin") Admin admin) {
        if (!UrlCompare.findUrl(url, "/admin/delete")) {
            return new RestResult<>(403, null, "权限不够");
        }
        if (admin.getAid() == aid) { //删除自己时
            return new RestResult<>(500, null, "删除失败,你已登陆");
        }
        Integer result = adminService.deleteAdmin(aid);
        if (result == 1) {
            return new RestResult<>(200, null, "删除成功");
        } else {
            return new RestResult<>(500, null, "删除失败");
        }
    }

}
