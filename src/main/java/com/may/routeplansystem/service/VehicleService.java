package com.may.routeplansystem.service;

import com.may.routeplansystem.entity.po.VehicleMessage;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author:dengsiyuan
 * @Date:2018/9/24 20:34
 */
@Service
public interface VehicleService {
    /**
     * 用户车辆导入
     *
     * @param vehicle
     * @return -1:导入失败
     */
    void vehicleRegister(VehicleMessage vehicle);

    /**
     * 根据用户查询车辆信息
     *
     * @param userId
     * @return vehicleMessage
     */
    List<VehicleMessage> userVehicleMessage(int userId);

    /**
     * 根据车辆Id查询车辆信息
     *
     * @param vehicleId
     * @return VehicleMessage
     */
    Object vehicleMessage(int vehicleId);

    /**
     * 删除车辆信息
     *
     * @param vehicleIdList
     * @return true/false
     **/
    void deleteVehicle(List<Integer> vehicleIdList);

    /**
     * 批量导入车辆
     *
     * @param mFile
     * @param request
     * @param questionId
     */
    void vehicleBatchImport(MultipartFile mFile, HttpServletRequest request, int questionId);

}
