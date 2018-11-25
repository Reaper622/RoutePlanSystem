package com.may.routeplansystem.service;

import com.may.routeplansystem.pojo.VehicleMessage;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
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
     * @return -1:导入失败
     * */
    Object vehicleRegister(VehicleMessage vehicleMessage, int questionId);
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

    /**
     * 判断文件类型
     * @param fileName
     * @param mFile
     * @param request
     * @return 返回文件判断结果
     * */
    String batchImport(String fileName, MultipartFile mFile, HttpServletRequest request, String user, int questionId);

    /**
     * 导入数据，并返回结果
     * @param wb
     * @param tempFile
     * @return 返回具体导入结果
     * */
    String readExcel(Workbook wb, File tempFile,HttpSession session, int questionId);

}
