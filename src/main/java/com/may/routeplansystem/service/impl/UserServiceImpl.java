package com.may.routeplansystem.service.impl;

import com.may.routeplansystem.constant.StatusCode;
import com.may.routeplansystem.dao.UserDao;
import com.may.routeplansystem.pojo.UserMessage;
import com.may.routeplansystem.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailSenderAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author:dengsiyuan
 * @Date:2018/9/24 12:14
 */
@Service
@Configuration
@Import(value = MailSenderAutoConfiguration.class)
public class UserServiceImpl implements UserService {

    String attribute = "user";
    String codeAttribute = "verifyCode";
    private String from = "campus.mis@foxmail.com";
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UserDao userDao;

    /**
     * 验证登陆信息是否正确
     * @param userId
     * @param password
     * @param code
     * @param session
     * @return true:验证通过 false:验证不通过
     * */
    @Override
    public Object userLogin(String userId, String password, String code, HttpSession session){
        String sessionCode = (String) session.getAttribute(codeAttribute);
        Map map = new HashMap<String,Object>(16);
        UserMessage userMessage = new UserMessage();
        try {
            if(session.getAttribute(attribute) != null){
                map.put("userMessage",userDao.userMessage(userId));
                map.put("status", StatusCode.SUCCESS);
            }else {
                if (userId != null && password !=null && code != null){
                    if (code.equalsIgnoreCase(sessionCode)){
                        userMessage.setUserId(userId);
                        userMessage.setPassword(password);
                        if(userDao.isLogin(userMessage) != null){
                            session.setAttribute("user",userMessage.getUserId());
                            session.setMaxInactiveInterval(3600);
                            map.put("userMessage",userDao.userMessage(userId));
                            map.put("status",StatusCode.SUCCESS);
                            logger.info("用户"+userId+"登录");
                        }else {
                            map.put("status",StatusCode.MESSAGE_ERROR);
                        }
                    }else {
                        map.put("status",StatusCode.CODE_FAIL);
                    }
                }else {
                    map.put("status",StatusCode.MESSAGE_NULL);
                }
            }
        }catch (Exception e){
            logger.error(e.getClass()+"{}",e);
            map.put("status", StatusCode.FAIL);
        }
        return map;
    }

    /**
     * 用户注册
     * @param userMessage
     * @param mailCode
     * @param rePassword
     * @param session
     * @return -1:注册失败
     */
    @Override
    public Object userRegister(UserMessage userMessage,String mailCode,String rePassword,HttpSession session) {
        Map map = new HashMap<String,String>(16);
        String verifyCode = (String) session.getAttribute("mailCode");
        try {
            if(userMessage == null){
                map.put("status",StatusCode.MESSAGE_NULL);
            }else {
                if(userDao.userMessage(userMessage.getUserId()) != null){
                    map.put("status",StatusCode.MESSAGE_EXIST);
                }else {
                    if (rePassword.equals(userMessage.getPassword())){
                        if(mailCode.equals(verifyCode)){
                            if(userDao.insertUser(userMessage) == -1){
                                map.put("status",StatusCode.PERMISSION_FAIL);
                            }else {
                                map.put("status",StatusCode.SUCCESS);
                                logger.info("用户"+userMessage.getUserId()+"完成注册");
                            }
                        }else {
                            map.put("status",StatusCode.CODE_FAIL);
                        }
                    }else {
                        map.put("status",StatusCode.MESSAGE_ERROR);
                    }
                }
            }
        }catch (Exception e){
            map.put("status",StatusCode.FAIL);
            logger.error(e.getClass()+"{}",e);
        }
        return map;
    }

    /**
     * 用户邮箱验证码发送
     * @param eMail
     * @param session
     * @return 邮件
     * */
    @Override
    public Object sendVerifyMail(String eMail,HttpSession session) {
        Map map = new HashMap<String,Object>(16);
        try {
            if(userDao.userEmail(eMail) == null){
                //生成验证码
                String base = "abcdefghijklmnopqrstuvwxyz0123456789";
                Random random = new Random();
                StringBuffer code = new StringBuffer();
                for (int i = 0; i < 5; i++) {
                    int number = random.nextInt(base.length());
                    code.append(base.charAt(number));
                }
                //存入Session
                session.setAttribute("mailCode",code.toString());
                MimeMessage message = null;
                //创建一个邮件
                message = mailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message, true);
                //设置邮件头部
                helper.setFrom(from);
                helper.setTo(eMail);
                helper.setSubject("Path Security");
                Date date = new Date();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                //拼接邮件内容
                StringBuffer sb = new StringBuffer();
                sb.append("<div style='position: relative; margin: 0 auto; width: 450px;height: 350px;'>")
                        .append("<h1 align='center'><strong>Path</strong></h1>")
                        .append("<h5>您好：</h5>")
                        .append("<h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;欢迎您通过邮箱验证码来验证您的身份！</h5>")
                        .append("<h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;快！使用<div style='position: relative; " +
                                "margin: 0 auto; width: 100px;height: 45px; text-align: center; background-color: #EEE'>" +
                                "<font size='5' style='position: relative; top: 6px;'>"
                                + code.toString()
                                + "</font></div>去验证您的身份吧！切记不要告诉他人哦！</h5>")
                        .append("<h5 style='position:relative; float: right; margin-bottom: 0'>Path</h5><br><br>")
                        .append("<h5 style='position: relative; float: right; margin-top: 0'>" + simpleDateFormat.format(date) + "</h5></div>");
                helper.setText(sb.toString(), true);
                //发送邮件
                mailSender.send(message);
                map.put("status",StatusCode.SUCCESS);
                logger.info("发送用户注册验证码");
            }else {
                map.put("status",StatusCode.MESSAGE_ERROR);
            }
        } catch (MessagingException e) {
            logger.error(e.getClass() + "{}", e);
            map.put("status",StatusCode.FAIL);
        }
        return map;
    }

    /**
     * 注册userId唯一性验证
     *
     * @param userId
     * @return userMessage
     */
    @Override
    public Object userIdVerify(String userId) {
        Map map = new HashMap<String,Object>(16);
        try {
            if(userDao.userMessage(userId) != null){
                map.put("status",StatusCode.MESSAGE_ERROR);
            }else {
                map.put("status",StatusCode.SUCCESS);
            }
        }catch (Exception e){
            map.put("status",StatusCode.FAIL);
        }
        return map;
    }
}
