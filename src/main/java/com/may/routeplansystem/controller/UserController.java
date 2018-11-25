package com.may.routeplansystem.controller;import com.may.routeplansystem.constant.StatusCode;import com.may.routeplansystem.dao.UserDao;import com.may.routeplansystem.pojo.UserMessage;import com.may.routeplansystem.service.UserService;import com.may.routeplansystem.service.VehicleService;import com.may.routeplansystem.util.VerifyCodeImageUtil;import org.slf4j.Logger;import org.slf4j.LoggerFactory;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.web.bind.annotation.*;import javax.imageio.ImageIO;import javax.servlet.http.Cookie;import javax.servlet.http.HttpServletRequest;import javax.servlet.http.HttpServletResponse;import javax.servlet.http.HttpSession;import java.awt.image.BufferedImage;import java.io.OutputStream;import java.util.HashMap;import java.util.Map;import java.util.Random;/** * @author:dengsiyuan * @Date:2018/11/11 22:45 */@RestController@RequestMapping("userSystem")public class UserController {    @Autowired    private UserService userService;    String attribute = "user";    String codeAttribute = "verifyCode";    /**     * @api {GET} /userSystem/verifyCode 获取登录验证码     * @apiDescription 通过该接口获取登录验证码，并存入session     * @apiGroup UserSystem     * @apiSuccessExample Success-Response     *     HTTP/1.1 200 OK     *     验证码图片     */    @GetMapping(value = "/verifyCode")    public void verifyCode(HttpServletResponse response, HttpServletRequest request) throws Exception {        System.out.println("验证码：" + request.getSession().getId());        //利用图片工具生成图片        //第一个参数是生成的验证码，第二个参数是生成的图片        Object[] objs = VerifyCodeImageUtil.createImage();        //将验证码存入Session        request.getSession().setAttribute(codeAttribute, "abcd");        //将图片输出给浏览器        BufferedImage image = (BufferedImage) objs[1];        response.setContentType("image/png");        OutputStream os = response.getOutputStream();        ImageIO.write(image, "png", os);    }    /**     * @api {POST} /userSystem/session/user 用户登陆     * @apiDescription 通过userId和password并验证验证码是否正确进行登陆     * @apiGroup UserSystem     * @apiParam {String} userId 用户Id     * @apiParam {String} password 用户密码     * @apiParam {String} code 验证码     * @apiSuccessExample Success-Response     *     HTTP/1.1 200 OK     *     {     *       "userMessage": {     *         "userId": "1",     *         "userName": "userName",     *         "password": null,     *         "email": "1058752198@qq.com"     *     },     *       "status":1     *     }     * @apiError 0 该功能存在异常     * @apiError 2 用户名或密码输入错误     * @apiError 3 验证码为空     * @apiError 4 请填写完整     * @apiError 5 您未登录，请登陆后操作     */    @PostMapping(value = "/session/user")    public Object userLogin(String userId, String password, String code, HttpSession session){        System.out.println("用户登录： " + session.getId());        return userService.userLogin(userId,password,code,session);    }    /**     * @api {DELETE} /userSystem/session/user 用户注销     * @apiDescription 注销登录     * @apiGroup UserSystem     * @apiSuccessExample Success-Response     *     HTTP/1.1 200 OK     *     {     *       "status":1     *     }     * @apiError 0 存在异常     * @apiError 1 注销成功     * @apiError 2 注销失败     */    @DeleteMapping(value = "/session/user")    public Object userLogOut(HttpServletRequest request){        Map map = new HashMap<String,String>(16);        try{            request.getSession().invalidate();            if(request.getSession().getAttribute(attribute) == null){                map.put("status",StatusCode.SUCCESS);            }else {                map.put("status",StatusCode.MESSAGE_ERROR);            }        }catch (Exception e){            e.printStackTrace();            map.put("status",StatusCode.FAIL);        }        return map;    }    /**     * @api {POST} /userSystem/user 用户注册     * @apiDescription 新用户通过填写相关信息进行注册     * @apiGroup UserSystem     * @apiParam {int} userId 用户Id     * @apiParam {String} userName 用户名     * @apiParam {String} eMail 用户邮箱     * @apiParam {String} password 密码     * @apiParam {String} rePassword 重复密码     * @apiParam {String} mailCode 邮箱验证码     * @apiSuccessExample Success-Response     *     HTTP/1.1 200 OK     *     {     *       "status":1     *     }     * @apiError 0 存在异常     * @apiError 2 两次密码不一致     * @apiError 3 邮箱验证码错误     * @apiError 4 信息输入不完整     * @apiError 5 注册失败     * @apiError 6 该userId已存在     */    @PostMapping(value = "/user")    public Object userRegister(UserMessage userMessage, String mailCode, String rePassword, HttpSession session){        return userService.userRegister(userMessage,mailCode,rePassword,session);    }    /**     * @api {GET} /user/eMailCode 邮箱验证码发送     * @apiDescription 用户注册进行绑定邮箱     * @apiGroup UserSystem     * @apiSuccessExample Success-Response     *     HTTP/1.1 200 OK     *     {     *       "status":1     *     }     * @apiError 0 存在异常     * @apiError 2 该邮箱已经存在     * @apiError 1 发送成功     * @apiError 5 发送失败，稍后重试     */    @GetMapping(value = "/user/eMailCode")    public Object verifyMail(String eMail, HttpSession session){        return userService.sendVerifyMail(eMail,session);    }    /**     * @api {GET} /user/userId 用户Id唯一性验证     * @apiDescription 用户注册Id的唯一性     * @apiGroup UserSystem     * @apiSuccessExample Success-Response     *     HTTP/1.1 200 OK     *     {     *       "status":1     *     }     * @apiError 0 存在异常     * @apiError 1 该userId可用     * @apiError 2 该userId已存在     */    @GetMapping(value = "/user/userId")    public Object userIdVerify(String userId){        return userService.userIdVerify(userId);    }}