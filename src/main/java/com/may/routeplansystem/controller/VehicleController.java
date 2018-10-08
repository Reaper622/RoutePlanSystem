package com.may.routeplansystem.controller;

import com.may.routeplansystem.constant.StatusCode;
import com.may.routeplansystem.pojo.VehicleMessage;
import com.may.routeplansystem.service.VehicleService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author:dengsiyuan
 * @Date:2018/9/24 18:35
 */
@RestController
public class VehicleController {

    String userAttribute = "user";

    @Autowired
    private VehicleService vehicleService;

    /**
     * 用户汽车信息的录入
     * @param vehicleMessage
     * @param session
     * */
    @ApiOperation("用户汽车信息的录入")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "questionId",dataType = "int",value = "订单编号"),
            @ApiImplicitParam(paramType = "query",name = "type",dataType = "String",value = "车辆类型"),
            @ApiImplicitParam(paramType = "query",name = "capacity",dataType = "float",value = "车辆载货量"),
            @ApiImplicitParam(paramType = "query",name = "oil",dataType = "float",value = "排量"),
            @ApiImplicitParam(paramType = "query",name = "price",dataType = "float",value = "价格"),
            @ApiImplicitParam(paramType = "query",name = "delFlag",dataType = "int",value = "状态"),
    })
    @ApiResponses({
            @ApiResponse(code = 0,message = "该功能存在异常"),
            @ApiResponse(code = 1,message = "车辆导入成功"),
            @ApiResponse(code = 2,message = "导入失败"),
            @ApiResponse(code = 4,message = "请完善信息"),
            @ApiResponse(code = 5,message = "未登录")
    })
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
     * 用户车辆信息查询
     * @param session
     * @return 1.vehicleMessage:车辆信息 2.错误信息
     * */
    @ApiOperation("用户车辆信息")
    @ApiResponses({
            @ApiResponse(code = 0,message = "该功能存在异常"),
            @ApiResponse(code = 4,message = "查询为空"),
            @ApiResponse(code = 5,message = "未登录")
    })
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
