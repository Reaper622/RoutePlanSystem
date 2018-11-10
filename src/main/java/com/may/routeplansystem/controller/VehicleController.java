package com.may.routeplansystem.controller;

import com.alibaba.fastjson.JSONObject;
import com.may.routeplansystem.constant.StatusCode;
import com.may.routeplansystem.pojo.VehicleMessage;
import com.may.routeplansystem.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 车辆管理模块
 * @author:dengsiyuan
 * @Date:2018/9/24 18:35
 */
@RestController
@RequestMapping(value = "/vehicleSystem")
public class VehicleController {

    String userAttribute = "user";

    @Autowired
    private VehicleService vehicleService;

    /**
     * @api {POST} /vehicleSystem/user/vehicle 用户车辆信息的录入
     * @apiDescription 用户车辆信息的录入
     * @apiGroup vehicleSystem
     * @apiParamExample {json} Request-Example:
     *     {
     *         "questionId":11,{int}订单编号
     *         "type":皮卡,{String}车辆的类型
     *         "capacity":55.6,{float}载货量
     *         "oil":5.5,{float}排量
     *         "price":200.5,{float}价格
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
    public Object userVehicleRegister(@RequestBody JSONObject vehicleJson, HttpSession session){
        VehicleMessage vehicleMessage = JSONObject.toJavaObject(vehicleJson,VehicleMessage.class);
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
     * @api {GET} /vehicleSystem/user/vehicle 当前登录用户车辆信息查询
     * @apiDescription 查询
     * @apiGroup vehicleSystem
     * @apiSuccessExample Success-Response:
     *     HTTP/1.1 200 OK
     *     {
     *             "vehicleId":1,{int}车辆编号
     *             "questionId":1,{int}
     *             "type":皮卡,{String}车型
     *             "capacity":500,{float}载货量
     *             "oil":4.3,{float}排量
     *             "date":2018-09-26 22:38:14录入时间
     *             "price":253.3,{float}价格
     *             "delFlag":0,{int}0:存在 1:已删除
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
    /**
     * @api {DELETE} /vehicleSystem/user/vehicle 用户车辆的删除
     * @apiDescription 删除用户车辆
     * @apiGroup vehicleSystem
     * @apiParam {int} vehicleId 车辆编号
     * @apiSuccessExample Success-Response:
     *     HTTP/1.1 200 OK
     *     {
     *        "status"：1
     *     }
     * @apiError 0 该功能存在异常
     * @apiError 2 请登陆后操作
     * @apiError 4 不存在该车辆，刷新页面后重新操作
     * @apiError 5 删除失败
     * @apiErrorExample {json} Error-Response:
     *     HTTP/1.1 0 存在异常
     *     {
     *        "status": "0"
     *     }
     * */
    public Object userVehicleDelete(int vehicleId,HttpSession session){
        Map map = new HashMap<String, Object>(16);
        try {
            if(session.getAttribute(userAttribute) != null){
                if(vehicleService.userVehicleMessage(vehicleId) !=null){
                    if(vehicleService.deleteVehicle(vehicleId)){
                        map.put("status",StatusCode.SUCCESS);
                    }else {
                        map.put("status",StatusCode.PERMISSION_FAIL);
                    }
                }else {
                    map.put("status",StatusCode.MESSAGE_NULL);
                }
            }else {
                map.put("status",StatusCode.MESSAGE_ERROR);
            }
        }catch (Exception e){
            map.put("status",StatusCode.FAIL);
        }
        return map;
    }
}
