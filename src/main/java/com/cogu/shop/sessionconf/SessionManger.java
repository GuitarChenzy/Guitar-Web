package com.cogu.shop.sessionconf;

import javax.servlet.http.HttpSession;

/**
 * Created by Cogu on 2018/6/14.
 */
public class SessionManger {

    private static ThreadLocal<HttpSession> threadLocal = new InheritableThreadLocal<>();

    public static void createSession(HttpSession httpSession){
        threadLocal.set(httpSession);
    }



}
