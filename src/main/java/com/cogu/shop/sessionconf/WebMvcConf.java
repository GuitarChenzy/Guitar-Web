package com.cogu.shop.sessionconf;

import com.alibaba.fastjson.JSON;
import com.cogu.shop.controller.entity.RestResult;
import com.cogu.shop.exception.RestException;
import com.cogu.shop.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Cogu on 2018/6/14.
 */
@Configuration
public class WebMvcConf extends WebMvcConfigurerAdapter {
    @Autowired
    private UserSecurityInterceptor securityInterceptor;

    private final Logger logger = LoggerFactory.getLogger(WebMvcConfigurer.class);

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(securityInterceptor).addPathPatterns(
                "/purchase/**", "/goods/**", "/category/**", "/user/**",
                "/order/**", "/admin/**", "/message/**", "/role/**", "/url/**");//配置登录拦截器拦截路径
        //registry.addInterceptor(securityInterceptor).excludePathPatterns("/admin/login");
    }

    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        exceptionResolvers.add(new HandlerExceptionResolver() {
            public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
                RestException result;
                if (e instanceof ServiceException) {//业务失败的异常，如“账号或密码错误”
                    result = new RestException("501", "业务层出错：" + e.getMessage(), null);
                    logger.info(e.getMessage());
                } else if (e instanceof NoHandlerFoundException) {
                    result = new RestException("404", "页面 [" + request.getRequestURI() + "] 不存在", null);
                } else {
                    result = new RestException("500", "接口 [" + request.getRequestURI() + "] 错误，请联系管理员！", null);
                    String message;
                    if (handler instanceof HandlerMethod) {
                        HandlerMethod handlerMethod = (HandlerMethod) handler;
                        message = String.format("接口 [%s] 出现异常，方法：%s.%s，异常摘要：%s",
                                request.getRequestURI(),
                                handlerMethod.getBean().getClass().getName(),
                                handlerMethod.getMethod().getName(),
                                e.getMessage());
                    } else {
                        message = e.getMessage();
                    }
                    result.setMessage(message);
                    logger.error(message, e);
                }
                response.resetBuffer();
                ModelAndView modelAndView = new ModelAndView("error");
                modelAndView.addObject("error", result);
                return modelAndView;
            }

        });
    }

    private void responseResult(HttpServletResponse response, RestResult result) {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.setStatus(200);
        try {
            response.getWriter().write(JSON.toJSONString(result));
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }
    }

}