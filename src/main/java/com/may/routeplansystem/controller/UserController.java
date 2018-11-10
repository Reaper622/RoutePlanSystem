package com.may.routeplansystem.controller;

import com.may.routeplansystem.constant.StatusCode;
import com.may.routeplansystem.pojo.UserMessage;
import com.may.routeplansystem.service.UserService;
import com.may.routeplansystem.util.VerifyCodeImageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 用户管理模块
 * @author:dengsiyuan
 * @Date:2018/9/23 19:52
 */
@RestController
@RequestMapping("userSystem")
public class UserController {

    String attribute = "user";
    String codeAttribute = "verifyCode";

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    /**
     * @api {GET} /userSystem/verifyCode 获取登录验证码
     * @apiDescription 通过该接口获取登录验证码，并存入session
     * @apiGroup UserSystem
     * @apiSuccessExample Success-Response
     *     HTTP/1.1 200 OK
     *     验证码图片
     */
    @GetMapping(value = "/verifyCode")
    public void verifyCode(HttpServletResponse response, HttpSession session) throws Exception {
        //利用图片工具生成图片
        //第一个参数是生成的验证码，第二个参数是生成的图片
        Object[] objs = VerifyCodeImageUtil.createImage();
        //将验证码存入Session
        session.setAttribute("verifyCode", objs[0]);
        //将图片输出给浏览器
        BufferedImage image = (BufferedImage) objs[1];
        response.setContentType("image/png");
        OutputStream os = response.getOutputStream();
        ImageIO.write(image, "png", os);
    }

    /**
     * @api {POST} /userSystem/session/user 用户登陆
     * @apiDescription 通过userId和password并验证验证码是否正确进行登陆
     * @apiGroup UserSystem
     * @apiParam {String} userId 用户Id
     * @apiParam {String} password 用户密码
     * @apiParam {String} code 验证码
     * @apiSuccessExample Success-Response
     *     HTTP/1.1 200 OK
     *     {
     *       "status":1
     *     }
     * @apiError 0 该功能存在异常
     * @apiError 2 用户名或密码输入错误
     * @apiError 3 验证码为空
     * @apiError 4 请填写完整
     * @apiError 5 您未登录，请登陆后操作
     */
    @PostMapping(value = "/session/user")
    public Object userLogin(String userId, String password, String code, HttpSession session){
        Map map = new HashMap<String,String>(16);
        UserMessage userMessage = new UserMessage();
        try {
            if(session.getAttribute(attribute) != null){
                map.put("status",StatusCode.SUCCESS);
            }else {
                if (userId != null && password !=null && code != null){
                    if (code.equalsIgnoreCase((String) session.getAttribute(codeAttribute))){
                        userMessage.setUserId(userId);
                        userMessage.setPassword(password);
                        map.put("status", userService.userLogin(userMessage,session));
                    }else {
                        map.put("status",StatusCode.CODE_FAIL);
                    }
                }else {
                    map.put("status",StatusCode.MESSAGE_NULL);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("status", StatusCode.FAIL);
        }
        return map;
    }

    /**
     * @api {DELETE} /userSystem/session/user 用户注销
     * @apiDescription 注销登录
     * @apiGroup UserSystem
     * @apiSuccessExample Success-Response
     *     HTTP/1.1 200 OK
     *     {
     *       "status":1
     *     }
     * @apiError 0 存在异常
     * @apiError 1 注销成功
     * @apiError 2 注销失败
     */
    @DeleteMapping(value = "session/user")
    public Object userLogOut(HttpServletRequest request){
        Map map = new HashMap<String,String>(16);
        try{
            request.getSession().invalidate();
            if(request.getSession().getAttribute(attribute) == null){
                map.put("status",StatusCode.SUCCESS);
            }else {
                map.put("status",StatusCode.MESSAGE_ERROR);
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("status",StatusCode.FAIL);
        }
        return map;
    }

    /**
     * @api {POST} /userSystem/user 用户注册
     * @apiDescription 新用户通过填写相关信息进行注册
     * @apiGroup UserSystem
     * @apiParam {int} userId 用户Id
     * @apiParam {String} userName 用户名
     * @apiParam {String} eMail 用户邮箱
     * @apiParam {String} password 密码
     * @apiParam {String} rePassword 重复密码
     * @apiParam {String} mailCode 邮箱验证码
     * @apiSuccessExample Success-Response
     *     HTTP/1.1 200 OK
     *     {
     *       "status":1
     *     }
     * @apiError 0 存在异常
     * @apiError 2 两次密码不一致
     * @apiError 3 邮箱验证码错误
     * @apiError 4 信息输入不完整
     * @apiError 5 注册失败
     */
    @PostMapping(value = "/user")
    public Object userRegister(UserMessage userMessage,String mailCode,String rePassword,HttpSession session){
        Map map = new HashMap<String,String>(16);
        String verifyCode = (String) session.getAttribute("mailCode");
        try {
            if(userMessage == null){
                map.put("status",StatusCode.MESSAGE_NULL);
            }else {
                if (rePassword.equals(userMessage.getPassword())){
                    if(mailCode.equals(verifyCode)){
                        map.put("status",userService.userRegister(userMessage));
                    }else {
                        map.put("status",StatusCode.CODE_FAIL);
                    }
                }else {
                    map.put("status",StatusCode.MESSAGE_ERROR);
                }
            }
        }catch (Exception e){
            map.put("status",StatusCode.FAIL);
        }
        return map;
    }

    /**
     * @api {GET} /user/eMailCode 邮箱验证码发送
     * @apiDescription 用户注册进行绑定邮箱
     * @apiGroup UserSystem
     * @apiSuccessExample Success-Response
     *     HTTP/1.1 200 OK
     *     {
     *       "status":1
     *     }
     * @apiError 0 存在异常
     * @apiError 1 发送成功
     * @apiError 5 发送失败
     */
    @GetMapping(value = "/user/eMailCode")
    public Object verifyMail(String eMail, HttpSession session) throws Exception {
        Map map = new HashMap<String,Object>(16);
        try {
            String base = "abcdefghijklmnopqrstuvwxyz0123456789";
            Random random = new Random();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < 5; i++) {
                int number = random.nextInt(base.length());
                sb.append(base.charAt(number));
            }
            session.setAttribute("mailCode",sb.toString());
            String subject = "Mis-Lab Security";
            if(userService.sendVerifyMail(eMail,subject,sb.toString()) != null){
                map.clear();
                map.put("verifyMail", StatusCode.PERMISSION_FAIL);
            }
            else {
                map.clear();
                map.put("verifyMail", StatusCode.SUCCESS);
            }
        }catch (Exception e){
            logger.error(e.getClass()+"{}",e);
            map.clear();
            map.put("verifyMail",StatusCode.FAIL);
        }
        return map;
    }
}
