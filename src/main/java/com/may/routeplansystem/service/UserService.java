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
     * 验证登陆信息是否正确
     * @param userId
     * @param password
     * @param code
     * @param session
     * @return true:验证通过 false:验证不通过
     * */
    Object userLogin(String userId, String password, String code, HttpSession session);

    /**
     * 用户注册
     * @param userMessage
     * @param mailCode
     * @param rePassword
     * @param session
     * @return -1:注册失败
     * */
    Object userRegister(UserMessage userMessage,String mailCode,String rePassword,HttpSession session);

    /**
     * 用户邮箱验证码发送
     * @param eMail
     * @param session
     * @return 邮件
     * */
    Object sendVerifyMail(String eMail,HttpSession session);

    /**
     * 注册userId唯一性验证
     * @param userId
     * @return map
     * */
    Object userIdVerify(String userId);
}
