package com.may.routeplansystem.service;

import com.may.routeplansystem.pojo.VehicleMessage;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * @author:dengsiyuan
 * @Date:2018/9/24 20:34
 */
@Service
public interface VehicleService {
    /**
     * 用户车辆导入
     * @param vehicleMessage
     * @param session
     * @return -1:导入失败
     * */
    int vehicleRegister(VehicleMessage vehicleMessage, HttpSession session);
    /**
     * 用户车辆信息的查询
     * @param userId
     * @return vehicleMessage
     * */
    Object userVehicleMessage(int userId);
}
