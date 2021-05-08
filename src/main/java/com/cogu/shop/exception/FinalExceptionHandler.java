package com.cogu.shop.exception;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: Cogu
 * @Date: 2018/7/11 14:38
 * @Description:
 */
@RestController
public class FinalExceptionHandler implements ErrorController {

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping(value = "/error")
    public ModelAndView error(HttpServletRequest req) {
        ModelAndView modelAndView = new ModelAndView("error");
        RestException restException = new RestException();
        restException.setCode("404");
        restException.setData("访问的页面不存在！");
        modelAndView.addObject("error", restException);
        return modelAndView;
    }

}
