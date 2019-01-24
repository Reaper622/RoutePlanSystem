package com.may.routeplansystem.vehicle_excel_check.imp;

import com.may.routeplansystem.constant.ExcelColumn;
import com.may.routeplansystem.exception.NodeTransferException;
import com.may.routeplansystem.vehicle_excel_check.VehicelExcelCheck;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

@Component
public class VehicleExcelCheckOne implements VehicelExcelCheck {

    @Override
    public void check(Workbook workbook) {
        Sheet sheet = workbook.getSheetAt(0);
        Row row = sheet.getRow(0);
        IntStream.range(0, 4).forEach(i -> {
            String errorMessage = "请选择正确的表格";
            switch (i) {
                case 0:
                    if (!row.getCell(i).getStringCellValue().equals(ExcelColumn.VEHICLE_ONE)){
                        throw new NodeTransferException(errorMessage);
                    }
                    break;
                case 1:
                    if (!row.getCell(i).getStringCellValue().equals(ExcelColumn.VEHICLE_TWO)){
                        throw new NodeTransferException(errorMessage);
                    }
                    break;
                case 2:
                    if (!row.getCell(i).getStringCellValue().equals(ExcelColumn.VEHICLE_THREE)){
                        throw new NodeTransferException(errorMessage);
                    }
                    break;
                case 3:
                    if (!row.getCell(i).getStringCellValue().trim().equals(ExcelColumn.VEHICLE_FOUR)){
                        throw new NodeTransferException(errorMessage);
                    }
                    break;
                default:
                    throw new NodeTransferException(errorMessage);
            }
        });
    }
}
