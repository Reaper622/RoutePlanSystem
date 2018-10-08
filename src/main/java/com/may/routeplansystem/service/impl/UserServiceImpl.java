package com.may.routeplansystem.service.impl;

import com.may.routeplansystem.constant.StatusCode;
import com.may.routeplansystem.dao.UserDao;
import com.may.routeplansystem.pojo.UserMessage;
import com.may.routeplansystem.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * @author:dengsiyuan
 * @Date:2018/9/24 12:14
 */
@Service
public class UserServiceImpl implements UserService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserDao userDao;
    /**
     * 验证登陆信息是否正确
     * @param userMessage
     * @return true:验证通过 false:验证不通过
     * */
    @Override
    public int userLogin(UserMessage userMessage, HttpSession session) {
        try {
            if(userDao.isLogin(userMessage) != null){
                session.setAttribute("user",userMessage.getUserId());
                session.setMaxInactiveInterval(3600);
                return StatusCode.SUCCESS;
            }else {
                return StatusCode.MESSAGE_ERROR;
            }
        }catch (Exception e){
            logger.error(e.getClass()+"{}",e);
            return StatusCode.FAIL;
        }
    }

    /**
     * 用户注册
     *
     * @param userMessage
     * @return -1:注册失败
     */
    @Override
    public int userRegister(UserMessage userMessage) {
        try{
            if(userDao.insertUser(userMessage) == -1){
                return StatusCode.FAIL;
            }else {
                return StatusCode.SUCCESS;
            }
        }catch (Exception e){
            logger.error(e.getClass()+"{}",e);
            return StatusCode.FAIL;
        }
    }
}
