package com.may.routeplansystem.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.may.routeplansystem.constant.ExceptionMessage;
import com.may.routeplansystem.dao.QuestionDao;
import com.may.routeplansystem.dao.VehicleDao;
import com.may.routeplansystem.entity.po.Question;
import com.may.routeplansystem.exception.DatabaseException;
import com.may.routeplansystem.exception.ParameterException;
import com.may.routeplansystem.pojo.VehicleMessage;
import com.may.routeplansystem.service.VehicleService;
import com.may.routeplansystem.util.ExcelUtil;
import com.may.routeplansystem.util.ServiceUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.may.routeplansystem.util.ServiceUtil.*;
import static com.may.routeplansystem.constant.ExceptionMessage.*;

/**
 * @author:dengsiyuan
 * @Date:2018/9/24 20:52
 */
@Service
@Slf4j
public class VehicleServiceImpl implements VehicleService {

    private String log1, log2, log3, log4;
    String userAttribute = "user";
    private static String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    @Autowired
    VehicleDao vehicleDao;

    @Autowired
    QuestionDao questionDao;

    @Override
    public void vehicleRegister(JSONObject vehicleJson, int questionId) {
        VehicleMessage vehicleMessage = JSONObject.toJavaObject(vehicleJson, VehicleMessage.class);
        if (vehicleMessage == null) {
            throw new ParameterException(VEHICLE_CANNOT_NULL);
        }
        String currentTime = getFormatTime(LocalDateTime.now());
        vehicleMessage.setDate(currentTime);
        Question question = questionDao.findQuestionByQuestionId(questionId);
        Objects.requireNonNull(question, QUESTION_NULL_BY_QUESTIONID);
        String ownerId = String.valueOf(question.getUserId());
        vehicleMessage.setOwnerId(ownerId);
        vehicleMessage.setQuestionId(questionId);
        if (vehicleDao.insertVehicle(vehicleMessage) == -1) {
            throw new SqlExecuteException(ExceptionMessage.VEHICLE_STORE_FAILURE);
        }
    }

    public void checkQuestionId(int questionId) {
        Question question = questionDao.findQuestionByQuestionId(questionId);
        Objects.requireNonNull(question, QUESTION_NULL_BY_QUESTIONID);
    }

    private String getFormatTime(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern(TIME_FORMAT));
    }

    @Override
    public List<VehicleMessage> userVehicleMessage(int userId) {
        List<VehicleMessage> vehicleMessage = vehicleDao.searchVehicleByOwnId(userId);
        Objects.requireNonNull(vehicleMessage, VEHICLE_NULL_BY_USERID);
        return vehicleMessage;
    }

    @Override
    public VehicleMessage vehicleMessage(int vehicleId) {
        VehicleMessage vehicleMessage = vehicleDao.searchVehicleByVehicleId(vehicleId);
        Objects.requireNonNull(vehicleMessage, VEHICLE_NULL_BY_VEHICLEID);
        return vehicleMessage;
    }

    @Override
    public void deleteVehicle(ArrayList vehicleIdList) {
        if (vehicleIdList.isEmpty()){
            throw new ParameterException(VEHICLE_ID_LIST_EMPTY);
        }
        boolean flag = vehicleDao.deleteVehicle(vehicleIdList);
        checkSqlExecuted(flag, VEHICLE_DELETE_FAILURE);
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
        String filePath = getFilePath();
        String result = null;
        try {
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
                //根kbook据版本选择创建Wor的方式
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
                result = readExcel(wb, tempFile, request.getSession(), questionId);
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
        } catch (Exception e) {
            result = e.getMessage();
        }
        return result;
    }

    private String getFilePath() {
        String filePath = System.getProperties().getProperty("user.dir");
        return filePath.replace("RoutePlanSystem", "");
    }

    /**
     * 导入数据，并返回结果
     *
     * @param wb
     * @param tempFile
     * @return 返回具体导入结果
     */
    @Override
    public String readExcel(Workbook wb, File tempFile, HttpSession session, int questionId) {
        //错误信息接收器
        String errorMsg = "";
        //得到第一个sheet
        Sheet sheet = wb.getSheetAt(0);
        //得到sheet中的行数
        int totalRows = sheet.getPhysicalNumberOfRows();
        //总列数
        int totalCells = 0;
        //得到sheet的列数,从第2行起
        if (totalRows >= 2 && sheet.getRow(1) != null) {
            totalCells = sheet.getRow(1).getPhysicalNumberOfCells();
            log2 = "共有" + totalRows + "行" + totalCells + "列";
        }
        List<VehicleMessage> vehicleMessagesList = new ArrayList<>(16);

        //循环excel行数，从第二行开始（标题不入库）
        for (int r = 1; r < totalRows; r++) {
            VehicleMessage vehicleMessage = new VehicleMessage();
            vehicleMessage.setQuestionId(questionId);
            String rowMessage = "";
            Row row = sheet.getRow(r);
            String type = "";
            float capacity = 0;
            float oil = 0;
            float price = 0;

            //循环Excel的列
            for (int c = 0; c < totalCells; c++) {
                Cell cell = row.getCell(c);
                if (c == 0) {
                    type = cell.getStringCellValue();
                    if (type.length() > 10) {
                        rowMessage += "车辆类型的字数不能超过10；\n";
                    } else {
                        vehicleMessage.setType(type);
                    }
                } else if (c == 1) {
                    capacity = (float) cell.getNumericCellValue();
                    if (capacity == 0) {
                        rowMessage += "载重量不能为空；";
                    } else {
                        vehicleMessage.setCapacity(capacity);
                    }
                } else if (c == 2) {
                    oil = (float) cell.getNumericCellValue();
                    if (oil == 0) {
                        rowMessage += "排量不能为空；";
                    } else {
                        vehicleMessage.setOil(oil);
                    }
                } else if (c == 3) {
                    price = (float) cell.getNumericCellValue();
                    if (price == 0) {
                        rowMessage += "成本/价格不能为空；";
                    } else {
                        vehicleMessage.setPrice(price);
                    }
                }
            }
            //拼接每行的错误提示
            if (!StringUtils.isEmpty(rowMessage)) {
                errorMsg += "\n第" + (r + 1) + "行，" + rowMessage;
                log4 = "导入失败";
            } else {
                vehicleMessagesList.add(vehicleMessage);
            }
        }
        //删除上传的临时文件
        if (tempFile.exists()) {
            tempFile.delete();
        }

        log3 = "开始导入";
        //全部验证通过才导入到数据库
        if (StringUtils.isEmpty(errorMsg)) {
            String result = null;
            for (VehicleMessage vehicleMessage1 : vehicleMessagesList) {
                if ((int) ((Map) vehicleRegister(vehicleMessage1, questionId)).get("status") == 1) {
                    log4 = "导入成功";
                    result = "导入成功";
                } else {
                    result = "请更改后重新导入";
                }
            }
            errorMsg += result;
        }
        log.info(log1);
        log.info(log2);
        log.info(log3);
        log.info(log4);
        return errorMsg;
    }
}
