package com.cogu.shop.controller;

import cn.dsna.util.images.ValidateCode;
import com.cogu.shop.controller.entity.RestResult;
import com.cogu.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author: Cogu
 * @Date: 2018/7/10 22:27
 * @Description:
 */
@RestController
public class MailController {

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    UserService userService;

    @Value("${spring.mail.username}")
    private String username;

    private String code;

    @GetMapping("/send")
    public RestResult<Integer> send(@RequestParam("url") String url) {
        if (url == null || url.equals("")) {
            return new RestResult<>(500, null, "发送失败");
        }
        SimpleMailMessage mainMessage = new SimpleMailMessage();
        mainMessage.setFrom(username);
        mainMessage.setTo(url);
        mainMessage.setSubject("验证码");
        ValidateCode validateCode = new ValidateCode(180, 40, 4, 80);
        String code = validateCode.getCode();
        mainMessage.setText(code);
        this.code = code;
        javaMailSender.send(mainMessage);
        System.out.println("邮箱验证码：" + code);
        return new RestResult<>(200, null, "发送成功");
    }

    @PostMapping("/pwd")
    public ModelAndView password(@RequestParam("code") String code,
                                 @RequestParam("password") String password,
                                 @RequestParam("email") String email) {
        ModelAndView modelAndView = new ModelAndView();
        if(password.equals("")){
            modelAndView.addObject("message", "修改失败：密码格式错误");
            modelAndView.setViewName("/password");
            return modelAndView;
        }
        if (!code.equalsIgnoreCase(this.code) || email.equals("")) {
            modelAndView.addObject("message", "修改失败：验证码错误");
            modelAndView.setViewName("/password");
        } else {
            if (userService.updatePassword(email, password.trim()) == 1) {
                modelAndView.setViewName("/login");
            } else {
                modelAndView.addObject("message", "修改失败：邮箱不存在");
                modelAndView.setViewName("/password");
            }
        }
        return modelAndView;
    }

}
