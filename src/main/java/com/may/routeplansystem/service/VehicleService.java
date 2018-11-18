package com.may.routeplansystem.service;

import com.may.routeplansystem.pojo.VehicleMessage;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

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
    Object vehicleRegister(VehicleMessage vehicleMessage, HttpSession session);
    /**
     * 根据用户查询车辆信息
     * @param userId
     * @return vehicleMessage
     * */
    Object userVehicleMessage(int userId);

    /**
     * 根据车辆Id查询车辆信息
     * @param vehicleId
     * @return VehicleMessage
     * */
    Object vehicleMessage(int vehicleId);

    /**
     * 删除车辆信息
     * @param vehicleIdList
     * @return true/false
     **/
    boolean deleteVehicle(ArrayList vehicleIdList);

}
