package com.may.routeplansystem.controller;

import com.may.routeplansystem.constant.StatusCode;
import com.may.routeplansystem.pojo.UserMessage;
import com.may.routeplansystem.service.UserService;
import com.may.routeplansystem.util.VerifyCodeImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

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
    @RequestMapping(value = "/verifyCode",method = RequestMethod.GET)
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
    @RequestMapping(value = "/session/user",method = RequestMethod.POST)
    public Object userLogin(String userId, String password, String code, HttpSession session){
        Map map = new HashMap<String,String>(16);
        UserMessage userMessage = new UserMessage();
        try {
            if(session.getAttribute(attribute) != null){
                map.put("status",StatusCode.SUCCESS);
            }else {
                if (userId != null && password !=null){
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
            map.put("status", StatusCode.FAIL);
        }
        return map;
    }

    /**
     * @api {DELETE} /userSystem/session/user 用户登陆
     * @apiDescription 注销登录
     * @apiGroup UserSystem
     * @apiSuccessExample Success-Response
     *     HTTP/1.1 200 OK
     *     {
     *       "status":1
     *     }
     * @apiError 0 存在异常
     * @apiError 2 注销失败
     */
    @RequestMapping(value = "session/user",method = RequestMethod.DELETE)
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
     * @apiParam {int} userId
     * @apiParam {String} userName
     * @apiParam {String} eMail
     * @apiParam {String} password
     * @apiParam {String} rePassword
     * @apiParam {String} mailCode
     * @apiSuccessExample Success-Response
     *     HTTP/1.1 200 OK
     *     {
     *       "status":1
     *     }
     * @apiError 0 存在异常
     * @apiError 2 两次密码不一致
     * @apiError 3 邮箱验证码错误
     * @apiError 4 信息输入不完整
     */
    @RequestMapping(value = "/user",method = RequestMethod.POST)
    public Object userRegister(UserMessage userMessage,String mailCode,String rePassword,HttpSession session){
        Map map = new HashMap<String,String>(16);
        String verifyCode = (String) session.getAttribute("mailCode");
        try {
            if(userMessage == null){
                map.put("status",StatusCode.MESSAGE_NULL);
            }else {
                if (rePassword.equals(userMessage.getPassword())){
                    if(true){
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
}
