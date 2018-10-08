package com.may.routeplansystem.controller;

import com.may.routeplansystem.constant.StatusCode;
import com.may.routeplansystem.pojo.UserMessage;
import com.may.routeplansystem.service.UserService;
import com.may.routeplansystem.util.VerifyCodeImageUtil;
import io.swagger.annotations.*;
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
 * @author:dengsiyuan
 * @Date:2018/9/23 19:52
 */
@RestController
@Api("用户模块API")
@RequestMapping("/userSystem")
public class UserController {

    String attribute = "user";
    String codeAttribute = "verifyCode";

    @Autowired
    private UserService userService;

    /**
     * 验证码的获取
     * @param session
     * @param response
     * */
    @ApiOperation("验证码获取")
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
     * 用户登录
     * @param userId
     * @param password
     * @param code
     * @param session
     * */
    @ApiOperation("用户登陆")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name="userId",dataType="String",required=true,value="用户的姓名"),
            @ApiImplicitParam(paramType="query",name="password",dataType="String",required=true,value="用户的密码"),
            @ApiImplicitParam(paramType="query",name="code",dataType="String",required=true,value="验证码")
    })
    @ApiResponses({
            @ApiResponse(code = 0,message = "该功能出现异常"),
            @ApiResponse(code = 1,message = "登陆成功"),
            @ApiResponse(code = 2,message = "用户名或密码有误"),
            @ApiResponse(code = 3,message = "验证码错误"),
            @ApiResponse(code = 4,message = "存在空值")
    })
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
     * 用户注销
     * @param request
     * */
    @ApiOperation("用户注销")
    @ApiResponses({
            @ApiResponse(code = 0,message = "此功能存在异常"),
            @ApiResponse(code = 1,message = "注销成功"),
            @ApiResponse(code = 2,message = "注销失败")
    })
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
     * 用户注册
     * @param userMessage
     * @param rePassword
     * @param mailCode
     * @param session
     * */
    @ApiOperation("用户注册")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "userId",required = true,value = "用户id"),
            @ApiImplicitParam(paramType = "query",name = "userName",required = true,value = "用户名"),
            @ApiImplicitParam(paramType = "query",name = "eMail",required = true,value = "用户邮箱"),
            @ApiImplicitParam(paramType = "query",name = "password",required = true,value = "密码"),
            @ApiImplicitParam(paramType = "query",name = "rePassword",required = true,value = "重复密码"),
            @ApiImplicitParam(paramType = "query",name = "mailCode",required = true,value = "邮箱验证码")
    })
    @ApiResponses({
            @ApiResponse(code = 0,message = "注册失败"),
            @ApiResponse(code = 1,message = "注册成功"),
            @ApiResponse(code = 2,message = "两次密码不一致"),
            @ApiResponse(code = 3,message = "验证码有误"),
            @ApiResponse(code = 4,message = "存在空值")
    })
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
