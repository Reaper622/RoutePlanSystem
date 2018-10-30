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

/**
 * @author:dengsiyuan
 * @Date:2018/9/24 12:14
 */
@Service
@Configuration
@Import(value = MailSenderAutoConfiguration.class)
public class UserServiceImpl implements UserService {

    private String from = "campus.mis@foxmail.com";

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JavaMailSender mailSender;

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
                return StatusCode.PERMISSION_FAIL;
            }else {
                return StatusCode.SUCCESS;
            }
        }catch (Exception e){
            logger.error(e.getClass()+"{}",e);
            return StatusCode.FAIL;
        }
    }

    /**
     * 用户邮箱验证码发送
     * @param to
     * @param subject
     * @param content
     * @return 邮件
     * */
    @Override
    public Object sendVerifyMail(String to,String subject,String content) {
        MimeMessage message = null;
        try {
            message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            StringBuffer sb = new StringBuffer();
            sb.append("<div style='position: relative; margin: 0 auto; width: 450px;height: 350px;'>")
                    .append("<h1 align='center'><strong>Path</strong></h1>")
                    .append("<h5>您好：</h5>")
                    .append("<h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;欢迎您通过邮箱验证码来验证您的身份！</h5>")
                    .append("<h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;快！使用<div style='position: relative; margin: 0 auto; width: 100px;height: 45px; text-align: center; background-color: #EEE'><font size='5' style='position: relative; top: 6px;'>" + content + "</font></div>去验证您的身份吧！切记不要告诉他人哦！</h5>")
                    .append("<h5 style='position:relative; float: right; margin-bottom: 0'>Path</h5><br><br>")
                    .append("<h5 style='position: relative; float: right; margin-top: 0'>" + simpleDateFormat.format(date) + "</h5></div>");
            helper.setText(sb.toString(), true);
            mailSender.send(message);
            return null;
        } catch (MessagingException e) {
            logger.error(e.getClass() + "{}", e);
            return null;
        }
    }
}
