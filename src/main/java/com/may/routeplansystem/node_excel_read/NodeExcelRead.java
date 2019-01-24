package com.may.routeplansystem.node_excel_read;

import com.may.routeplansystem.entity.po.NodePojo;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

public interface NodeExcelRead {

    List<NodePojo> read(Workbook wb, int questionId);
}
