package com.may.routeplansystem.vehicle_excel_read;

import com.may.routeplansystem.entity.po.VehicleMessage;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

public interface VehicelExcelRead {

    List<VehicleMessage> read(Workbook wb, int questionId);

}
