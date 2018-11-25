package com.may.routeplansystem.service.impl;

import com.may.routeplansystem.constant.StatusCode;
import com.may.routeplansystem.dao.VehicleDao;
import com.may.routeplansystem.pojo.NodePojo;
import com.may.routeplansystem.pojo.VehicleMessage;
import com.may.routeplansystem.service.VehicleService;
import com.may.routeplansystem.util.ExcelUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author:dengsiyuan
 * @Date:2018/9/24 20:52
 */
@Service
public class VehicleServiceImpl implements VehicleService {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    private String log1,log2,log3,log4;
    String userAttribute = "user";

    @Autowired
    VehicleDao vehicleDao;
    /**
     * 用户车辆导入
     * @param vehicleMessage
     * @return -1:导入失败
     */
    @Override
    public Object vehicleRegister(VehicleMessage vehicleMessage, HttpSession session, int questionId) {
        Map map = new HashMap<String,Integer>(16);
        try {
            if (session.getAttribute(userAttribute) != null){
                if(vehicleMessage != null){
                    long currentTime = System.currentTimeMillis();
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date = new Date(currentTime);
                    vehicleMessage.setDate(formatter.format(date));
                    vehicleMessage.setOwnerId((String) session.getAttribute(userAttribute));
                    vehicleMessage.setQuestionId(questionId);
                    if(vehicleDao.insertVehicle(vehicleMessage) == -1){
                        map.put("status",StatusCode.MESSAGE_ERROR);
                    }else {
                        map.put("status",StatusCode.SUCCESS);
                        logger.info(session.getAttribute(userAttribute)+"完成车辆录入");
                    }
                }else {
                    map.put("status",StatusCode.MESSAGE_NULL);
                }
            }else {
                map.put("status",StatusCode.PERMISSION_FAIL);
            }
        }catch (Exception e){
            map.put("status",StatusCode.FAIL);
            logger.error(e.getClass()+"{}",e);
        }
        return map;
    }

    /**
     * 根据用户查询车辆信息的查询
     *
     * @param userId
     * @return vehicleMessage
     */
    @Override
    public Object userVehicleMessage(int userId) {
        try {
            Object vehicleMessage = vehicleDao.searchVehicleByOwnId(userId);
            if(vehicleMessage != null){
                logger.info(userId+"查询车辆信息");
                return vehicleMessage;
            }else {
                return null;
            }
        }catch (Exception e){
            logger.error(e.getClass()+"{}",e);
            return null;
        }
    }

    /**
     * 根据车辆Id查询车辆信息
     *
     * @param vehicleId
     * @return VehicleMessage
     */
    @Override
    public Object vehicleMessage(int vehicleId) {
        try{
            VehicleMessage vehicleMessage = vehicleDao.searchVehicleByVehicleId(vehicleId);
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

    /**
     * 删除车辆信息
     *
     * @param vehicleIdList
     * @return true/false
     **/
    @Override
    public boolean deleteVehicle(ArrayList vehicleIdList) {
        if(vehicleDao.deleteVehicle(vehicleIdList)){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 判断文件类型
     *
     * @param fileName
     * @param mFile
     * @param request
     * @return 返回文件判断结果
     */
    @Override
    public String batchImport(String fileName, MultipartFile mFile, HttpServletRequest request, String user, int questionId) {
        String filePath = System.getProperties().getProperty("user.dir");
        filePath=filePath.replace("RoutePlanSystem", "");
        String result = null;
//        String user = (String) request.getSession().getAttribute("user");
        try {
                log1 = "用户" + user + "使用";
                File uploadDir = new File(filePath + user);
                //判断是否存在，不存在即创建
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }
                File tempFile = new File(filePath + user + File.separator + System.currentTimeMillis() + ".xlsx");
                //初始化输入流
                InputStream is = null;
                try {
                    //将上传的文件写入新的文件中
                    mFile.transferTo(tempFile);
                    //根据新建的文件实例化输入法
                    is = new FileInputStream(tempFile);
                    //根据版本选择创建Workbook的方式
                    Workbook wb = null;
                    //判断是2003还是2007
                    if (ExcelUtil.isExcel2003(fileName)) {
                        log1 += "2003版";
                        wb = new HSSFWorkbook(is);
                    } else if (ExcelUtil.isExcel2007(fileName)) {
                        log1 += "2007版";
                        wb = new XSSFWorkbook(is);
                    } else {
                        //不符合要求的文件类型
                        result = "文件类型不符合要求，请重新选择";
                    }
                    log1 += "申请导入：";
                    result = readExcel(wb,tempFile,request.getSession(), questionId);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (is != null) {
                        try {
                            is.close();
                        } catch (IOException e) {
                            is = null;
                            e.printStackTrace();
                        }
                    }
                }
        }catch (Exception e){
            result = e.getMessage();
        }
        return result;
    }

    /**
     * 导入数据，并返回结果
     *
     * @param wb
     * @param tempFile
     * @return 返回具体导入结果
     */
    @Override
    public String readExcel(Workbook wb, File tempFile,HttpSession session, int questionId) {
        //错误信息接收器
        String errorMsg = "";
        //得到第一个sheet
        Sheet sheet = wb.getSheetAt(0);
        //得到sheet中的行数
        int totalRows = sheet.getPhysicalNumberOfRows();
        //总列数
        int totalCells = 0;
        //得到sheet的列数,从第2行起
        if(totalRows >= 2 && sheet.getRow(1) != null){
            totalCells = sheet.getRow(1).getPhysicalNumberOfCells();
            log2 = "共有" + totalRows + "行" + totalCells + "列";
        }
        List<VehicleMessage> vehicleMessagesList = new ArrayList<>(16);
        VehicleMessage vehicleMessage = new VehicleMessage();
        vehicleMessage.setQuestionId(questionId);
        //循环excel行数，从第二行开始（标题不入库）
        for (int r = 1;r <totalRows; r++){
            String rowMessage = "";
            Row row = sheet.getRow(r);
            String type = "";
            float capacity = 0;
            float oil = 0;
            float price = 0;

            //循环Excel的列
            for(int c = 0; c <totalCells; c++){
                Cell cell = row.getCell(c);
                if (c == 0) {
                    type = cell.getStringCellValue();
                    if (type.length() > 10) {
                        rowMessage += "车辆类型的字数不能超过10；\n";
                    }  else {
                        vehicleMessage.setType(type);
                    }
                } else if (c == 1) {
                    capacity = (float) cell.getNumericCellValue();
                    if (capacity == 0) {
                        rowMessage += "载重量不能为空；";
                    }  else {
                        vehicleMessage.setCapacity(capacity);
                    }
                } else if (c == 2) {
                    oil = (float) cell.getNumericCellValue();
                    if (oil == 0) {
                        rowMessage += "排量不能为空；";
                    }  else {
                        vehicleMessage.setOil(oil);
                    }
                }else if (c == 3){
                    price = (float) cell.getNumericCellValue();
                    if(price == 0){
                        rowMessage += "成本/价格不能为空；";
                    }else {
                        vehicleMessage.setPrice(price);
                    }
                }
            }
            //拼接每行的错误提示
            if(!StringUtils.isEmpty(rowMessage)){
                errorMsg += "\n第"+(r+1)+"行，"+rowMessage;
                log4 = "导入失败";
            }else{
                vehicleMessagesList.add(vehicleMessage);
            }
        }
        //删除上传的临时文件
        if(tempFile.exists()){
            tempFile.delete();
        }

        log3 = "开始导入";
        //全部验证通过才导入到数据库
        if(StringUtils.isEmpty(errorMsg)){
            String result = null;
            for(VehicleMessage vehicleMessage1 : vehicleMessagesList){
                if((int)((Map)vehicleRegister(vehicleMessage1,session, questionId)).get("status") == 1){
                    log4 = "导入成功";
                    result = "导入成功";
                }
                else {
                    result = "请更改后重新导入";
                }
            }
            errorMsg += result;
        }
        logger.info(log1);
        logger.info(log2);
        logger.info(log3);
        logger.info(log4);
        return errorMsg;
    }
}
