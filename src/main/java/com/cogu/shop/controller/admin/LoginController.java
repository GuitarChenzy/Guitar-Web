package com.cogu.shop.controller.admin;

import cn.dsna.util.images.ValidateCode;
import com.cogu.shop.entity.vo.Admin;
import com.cogu.shop.entity.vo.Role;
import com.cogu.shop.entity.vo.Turl;
import com.cogu.shop.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cogu on 2018/6/11.
 */
@CrossOrigin("*")
@Controller
public class LoginController {

    private String check; // 验证码载体

    @Autowired
    private AdminService adminService;

    @GetMapping("/a_login")
    public ModelAndView index() {
        return new ModelAndView("admin/login");
    }

    @PostMapping("/a-login")
    public ModelAndView login(@RequestParam("username") String username,
                              @RequestParam("password") String password,
                              @RequestParam("checkcode") String checkcode,
                              HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        if (checkcode == null || checkcode != check) {
            modelAndView.addObject("message", "验证码错误");
            modelAndView.setViewName("admin/login");
        }
        Admin admin = adminService.findAdmin(username, password);
        if (admin == null) {
            modelAndView.addObject("message", "账号或密码错误");
            modelAndView.setViewName("admin/login");
        } else {
            if (request.getSession().getAttribute("admin") == null) {
                request.getSession().setAttribute("admin", admin);
            }
            modelAndView.addObject("admin", admin);
            //添加url访问表
            List<String> urlList = new ArrayList<>();
            for (Role role : admin.getRoleList()) {
                for (Turl turl : role.getTurlList()) {
                    urlList.add(turl.getFurl());
                }
            }
            if (request.getSession().getAttribute("url") == null) {
                request.getSession().setAttribute("url", urlList);
            }
            modelAndView.setViewName("admin/index");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/code", method = RequestMethod.GET)
    public void genarateCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ValidateCode validateCode = new ValidateCode(180, 40, 4, 80);
        String code = validateCode.getCode();
        System.out.println("验证码：" + code);
        request.getSession().setAttribute("code", code);
        check = code;
        response.setContentType("image/jpeg");
        validateCode.write(response.getOutputStream());
    }

}
