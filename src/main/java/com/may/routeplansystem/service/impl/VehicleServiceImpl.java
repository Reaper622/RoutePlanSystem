package com.may.routeplansystem.service.impl;

import com.may.routeplansystem.constant.StatusCode;
import com.may.routeplansystem.dao.VehicleDao;
import com.may.routeplansystem.pojo.VehicleMessage;
import com.may.routeplansystem.service.VehicleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author:dengsiyuan
 * @Date:2018/9/24 20:52
 */
@Service
public class VehicleServiceImpl implements VehicleService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    VehicleDao vehicleDao;
    /**
     * 用户车辆导入
     * @param vehicleMessage
     * @return -1:导入失败
     */
    @Override
    public int vehicleRegister(VehicleMessage vehicleMessage, HttpSession session) {
        String attribute = "user";
        try{
            long currentTime = System.currentTimeMillis();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date(currentTime);
            vehicleMessage.setDate(formatter.format(date));
            vehicleMessage.setOwnerId((String) session.getAttribute(attribute));
            if(vehicleDao.insertVehicle(vehicleMessage) == -1){
                return StatusCode.MESSAGE_ERROR;
            }else {
                return StatusCode.SUCCESS;
            }
        }catch (Exception e){
            logger.error(e.getClass()+"{}",e);
            return StatusCode.FAIL;
        }
    }

    /**
     * 用户车辆信息的查询
     *
     * @param userId
     * @return vehicleMessage
     */
    @Override
    public Object userVehicleMessage(int userId) {
        try {
            Object vehicleMessage = vehicleDao.searchVehicle(userId);
            if(vehicleMessage != null){
                return vehicleMessage;
            }else {
                return null;
            }
        }catch (Exception e){
            logger.error(e.getClass()+"{}",e);
            return null;
        }
    }
}
