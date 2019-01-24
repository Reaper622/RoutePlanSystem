package com.may.routeplansystem.vehicle_excel_read.imp;

import com.may.routeplansystem.constant.ExcelColumn;
import com.may.routeplansystem.entity.po.VehicleMessage;
import com.may.routeplansystem.exception.VehicleTransferException;
import com.may.routeplansystem.vehicle_excel_read.VehicelExcelRead;
import lombok.ToString;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VehicleExcelReadOne implements VehicelExcelRead {

    @Override
    public List<VehicleMessage> read(Workbook wb, int questionId) {
        Sheet sheet = wb.getSheetAt(0);
        int totalRow = sheet.getPhysicalNumberOfRows();
        int totalCell = sheet.getRow(1).getPhysicalNumberOfCells();
        List<VehicleMessage> vehicleMessages = new ArrayList<>(totalRow);
        for (int i = 1; i < totalRow; i++) {
            VehicleMessage vehicleMessage = new VehicleMessage();
            vehicleMessage.setQuestionId(questionId);
            Row row = sheet.getRow(i);
            for (int c = 0; c < totalCell; c++) {
                Cell cell = row.getCell(c);
                switch (c) {
                    case 0:
                        checkCellNull(i, cell, ExcelColumn.VEHICLE_ONE);
                        String type = cell.getStringCellValue();
                        if (type.length() > 10) {
                            throw new VehicleTransferException("第" + i + "行的车辆类型过长");
                        } else {
                            vehicleMessage.setType(type);
                        }
                        break;
                    case 1:
                        checkCellNull(i, cell, ExcelColumn.VEHICLE_TWO);
                        float capacity = (float) cell.getNumericCellValue();
                        if (capacity == 0) {
                            throw new VehicleTransferException("第" + i + "行的运钞量数据有问题");
                        } else {
                            vehicleMessage.setCapacity(capacity);
                        }
                        break;
                    case 2:
                        checkCellNull(i, cell, ExcelColumn.VEHICLE_THREE);
                        float oil = (float) cell.getNumericCellValue();
                        if (oil == 0) {
                            throw new VehicleTransferException("第" + i + "行的耗油量数据有问题");
                        } else {
                            vehicleMessage.setOil(oil);
                        }
                        break;
                    case 3:
                        checkCellNull(i, cell, ExcelColumn.VEHICLE_FOUR);
                        float price = (float) cell.getNumericCellValue();
                        if (price == 0) {
                            throw new VehicleTransferException("第" + i + "行的运费数据有问题");
                        } else {
                            vehicleMessage.setPrice(price);
                        }
                        break;
                    default:
                        throw new VehicleTransferException("上传失败");
                }
            }
            vehicleMessages.add(vehicleMessage);
        }
        return vehicleMessages;
    }

    private void checkCellNull(int row, Cell cell, String colnmnName) {
        if (cell == null) {
            throw new VehicleTransferException("第" + row + "行" + colnmnName + "不能为空");
        }
    }
}
