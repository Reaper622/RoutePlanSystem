package com.may.routeplansystem.dao;

import com.may.routeplansystem.pojo.VehicleMessage;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author:dengsiyuan
 * @Date:2018/9/23 20:49
 */
@Repository("vehicleDao")
public interface VehicleDao {

    /**
     * 用户车辆导入
     * @param vehicleMessage
     * @return -1:导入失败
     * */
    int insertVehicle(VehicleMessage vehicleMessage);

    /**
     * 用户信息查询
     * @param userId
     * @return vehicleMessage:返回车辆信息
     * */
    List<VehicleMessage> searchVehicleByOwnId(int userId);

    /**
     * 查询车辆信息
     * @param vehicleId
     * @return vehicleMessage
     * */
    VehicleMessage searchVehicleByVehicleId(int vehicleId);

    /**
     * 删除单条车辆信息
     * @param vehicleId
     * @return true/false
     */
    boolean deleteVehicle(int vehicleId);
}
