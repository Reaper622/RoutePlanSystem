package com.may.routeplansystem.service.impl;

import com.may.routeplansystem.constant.Constant;
import com.may.routeplansystem.constant.ProcessState;
import com.may.routeplansystem.dao.QuestionDao;
import com.may.routeplansystem.dao.VehicleDao;
import com.may.routeplansystem.entity.dto.ResponseEntity;
import com.may.routeplansystem.entity.po.Question;
import com.may.routeplansystem.entity.po.VehicleMessage;
import com.may.routeplansystem.exception.ParameterException;
import com.may.routeplansystem.exception.ProcessException;
import com.may.routeplansystem.service.VehicleService;
import com.may.routeplansystem.service.util.ServiceUtil;
import com.may.routeplansystem.util.DateUtil;
import com.may.routeplansystem.vehicle_excel_check.VehicelExcelCheck;
import com.may.routeplansystem.vehicle_excel_read.VehicelExcelRead;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.may.routeplansystem.constant.ExceptionMessage.*;
import static com.may.routeplansystem.service.util.ServiceUtil.checkSqlExecuted;


/**
 * @author 10587
 */
@Service
@Slf4j
public class VehicleServiceImpl implements VehicleService {

    @Resource
    private VehicleDao vehicleDao;

    @Resource
    private ApplicationContext applicationContext;

    @Resource
    private QuestionDao questionDao;

    @Resource
    private BatchImportService batchImportService;

    @Value("${excel.vehicle_check}")
    private String checkExcelVersion;

    @Value("${excel.vehicle_read}")
    private String readRuleVersion;

    @Override
    public void vehicleRegister(VehicleMessage vehicleMessage) {
        Objects.requireNonNull(vehicleMessage, VEHICLE_CANNOT_NULL);
        String currentTime = DateUtil.formatNowDateTimeToString(Constant.DATETIME_FORMAT);
        vehicleMessage.setDate(currentTime);
        boolean flag = vehicleDao.insertVehicle(vehicleMessage);
        ServiceUtil.checkSqlExecuted(flag);
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
    public void deleteVehicle(List<Integer> vehicleIdList) {
        if (vehicleIdList.isEmpty()) {
            throw new ParameterException(VEHICLE_ID_LIST_EMPTY);
        }
        boolean flag = vehicleDao.deleteVehicle((ArrayList) vehicleIdList);
        checkSqlExecuted(flag);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void vehicleBatchImport(MultipartFile mFile, HttpServletRequest request, int questionId) {
       batchImportService.batchImport(mFile, request, questionId, this::readExcelToDatabase);
    }

    private void readExcelToDatabase(Workbook wb, int questionId) {
        checkExcel(wb);
        VehicelExcelRead excelRead = (VehicelExcelRead) applicationContext.getAutowireCapableBeanFactory()
                .getBean(this.readRuleVersion);
        List<VehicleMessage> vehicleMessages = excelRead.read(wb, questionId);
        boolean loadFlag = vehicleMessages.stream().allMatch(vehicleDao::insertVehicle);
        checkQuestionStateAndUpdate(questionId);
        boolean updateStateFlag = questionDao.updateQuestionProcessState(ProcessState.LOAD_VEHICLE, questionId);
        ServiceUtil.checkSqlExecuted(loadFlag && updateStateFlag);
    }

    private void checkQuestionStateAndUpdate(int questionId) {
        Question question = questionDao.findQuestionByQuestionId(questionId);
        if (question == null) {
            throw new ProcessException("请先创建问题");
        }
        if (question.getProcessState() < ProcessState.LOAD_NODE) {
            throw new ProcessException("请先导入点");
        }
    }

    private void checkExcel(Workbook workbook) {
        VehicelExcelCheck excelCheckRule= (VehicelExcelCheck) applicationContext.getAutowireCapableBeanFactory()
                .getBean(this.checkExcelVersion);
        excelCheckRule.check(workbook);
    }
}
