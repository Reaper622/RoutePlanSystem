package com.may.routeplansystem.intercepter;

import com.may.routeplansystem.constant.ExceptionMessage;
import com.may.routeplansystem.constant.SessionMessage;
import com.may.routeplansystem.exception.AuthentationException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author 10587
 */
@Component
public class LoginIntercepter implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        boolean flag = session.getAttribute(SessionMessage.LOGIN_STATE) == null;
        if (!flag) {
            throw new AuthentationException(ExceptionMessage.LOGIN_STATE_EXCEPTION);
        }
        return true;
    }
}
