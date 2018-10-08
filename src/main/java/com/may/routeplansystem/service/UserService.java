package com.may.routeplansystem.service;

import com.may.routeplansystem.pojo.UserMessage;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * @author:dengsiyuan
 * @Date:2018/9/24 18:11
 */
@Service
public interface UserService {
    /**
     * 用户登陆是否成功的验证
     * @param userMessage
     * @param session
     * @return true:验证成功 false:验证失败
     * */
    int userLogin(UserMessage userMessage, HttpSession session);

    /**
     * 用户注册
     * @param userMessage
     * @return -1:注册失败
     * */
    int userRegister(UserMessage userMessage);
}
