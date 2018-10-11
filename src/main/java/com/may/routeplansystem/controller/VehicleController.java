package com.may.routeplansystem.controller;

import com.may.routeplansystem.constant.StatusCode;
import com.may.routeplansystem.pojo.VehicleMessage;
import com.may.routeplansystem.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 车辆管理模块
 * @author:dengsiyuan
 * @Date:2018/9/24 18:35
 */
@RestController
@RequestMapping(value = "vehicleSystem")
public class VehicleController {

    String userAttribute = "user";

    @Autowired
    private VehicleService vehicleService;

    /**
     * @api {POST} /vehicleSystem/user/vehicle 用户车辆信息的录入
     * @apiDescription 用户车辆信息的录入
     * @apiGroup vehicleSystem
     * @apiParam {int} questionId 订单编号
     * @apiParam {String} type 车辆的类型
     * @apiParam {float} capacity 载货量
     * @apiParam {float} oil 排量
     * @apiParam {float} price 价格
     * @apiParam {int} delFlag 车辆状态
     * @apiParamExample {json} Request-Example:
     *     {
     *         "questionId":11,
     *         "type":皮卡,
     *         "capacity":55.6,
     *         "oil":5.5,
     *         "price":200.5,
     *         "delFlag":0
     *     }
     * @apiSuccessExample Success-Response:
     *     HTTP/1.1 200 OK
     *     {
     *         "status":1
     *     }
     * @apiError 0 该功能存在异常
     * @apiError 2 导入失败，请重试
     * @apiError 4 请完善必要的信息
     * @apiError 5 您未登录，请登陆后操作
     * @apiErrorExample {json} Error-Response:
     *     HTTP/1.1 0 存在异常
     *     {
     *        "status": "0"
     *     }
     */
    @RequestMapping(value = "/user/vehicle",method = RequestMethod.POST)
    public Object userVehicleRegister(VehicleMessage vehicleMessage, HttpSession session){
        Map map = new HashMap<String,String>(16);
        try {
            if (session.getAttribute(userAttribute) != null){
                if(vehicleMessage != null){
                    map.put("status",vehicleService.vehicleRegister(vehicleMessage,session));
                }else {
                    map.put("status",StatusCode.MESSAGE_NULL);
                }
            }else {
                map.put("status",StatusCode.PERMISSION_FAIL);
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("status",StatusCode.FAIL);
        }
        return map;
    }

    /**
     * @api {GET} /vehicleSystem/user/vehicle 用户车辆信息的查询
     * @apiDescription 查询
     * @apiGroup vehicleSystem
     * @apiParam {int} questionId 订单编号
     * @apiParam {String} type 车辆的类型
     * @apiParam {float} capacity 载货量
     * @apiParam {float} oil 排量
     * @apiParam {float} price 价格
     * @apiParam {int} delFlag 车辆状态 0:空闲 1:在用
     * @apiSuccessExample Success-Response:
     *     HTTP/1.1 200 OK
     *     {
     *             "vehicleId":1
     *             "questionId":1
     *             "type":皮卡
     *             "capacity":500
     *             "oil":4.3
     *             "date":2018-09-26 22:38:14
     *             "price":253.3
     *             "delFlag":1
     *     }
     * @apiError 0 该功能存在异常
     * @apiError 4 查询为空
     * @apiError 5 您未登录，请登陆后操作
     * @apiErrorExample {json} Error-Response:
     *     HTTP/1.1 0 存在异常
     *     {
     *        "status": "0"
     *     }
     */
    @RequestMapping(value = "/user/vehicle",method = RequestMethod.GET)
    public Object userVehicleMessage(HttpSession session){
        Map map = new HashMap<String,String>(16);

        try {
            if(session.getAttribute(userAttribute) != null){
                int userId = Integer.parseInt((String) session.getAttribute(userAttribute));
                Object vehicleMessage = vehicleService.userVehicleMessage(userId);
                if(vehicleMessage != null){
                    map.put("status",vehicleMessage);
                }else {
                    map.put("status",StatusCode.MESSAGE_NULL);
                }
            }else {
                map.put("status",StatusCode.PERMISSION_FAIL);
            }
        }catch (Exception e){
            map.put("status",StatusCode.FAIL);
        }
        return map;
    }
}
