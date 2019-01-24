package com.may.routeplansystem.node_excel_read.imp;

import com.alibaba.fastjson.JSON;
import com.may.routeplansystem.constant.Constant;
import com.may.routeplansystem.constant.ExcelColumn;
import com.may.routeplansystem.entity.vo.NodeJsonResponse;
import com.may.routeplansystem.exception.NodeTransferException;
import com.may.routeplansystem.exception.ThreeServiceException;
import com.may.routeplansystem.exception.VehicleTransferException;
import com.may.routeplansystem.node_excel_read.NodeExcelRead;
import com.may.routeplansystem.entity.po.NodePojo;
import com.may.routeplansystem.util.BaiduMapApiUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class NodeExcelReadOne implements NodeExcelRead {

    @Override
    public List<NodePojo> read(Workbook wb, int questionId) {
        Sheet sheet = wb.getSheetAt(0);
        int totalRow = sheet.getPhysicalNumberOfRows();
        int totalCell = sheet.getRow(1).getPhysicalNumberOfCells();
        List<NodePojo> nodes = new ArrayList<>(totalRow);
        for (int i = 1; i < totalRow; i++) {
            NodePojo node = new NodePojo();
            node.setQuestionId(questionId);
            Row row = sheet.getRow(i);
            for (int c = 0; c < totalCell; c++) {
                Cell cell = row.getCell(c);
                switch (c) {
                    case 0:
                        checkCellNull(i, cell, ExcelColumn.NODE_ONE);
                        String name = cell.getStringCellValue();
                        if (name.length() > 10) {
                            throw new NodeTransferException("第" + i + "行的服务点名称过长");
                        } else {
                            node.setNodeName(name);
                        }
                        break;
                    case 1:
                        try {
                            checkCellNull(i, cell, ExcelColumn.NODE_TWO);
                            String address = cell.getStringCellValue();
                            if (!matchChinese(address)) {
                                throw new NodeTransferException("第" + i + "行的详细地址有误，只能是中文");
                            }
                            node.setNodeAddress(address);
                            String jsonResponse = BaiduMapApiUtil.getJsonOfOneNode(address, Constant.BAIDUMAP_AK);
                            NodeJsonResponse nodeJsonResponse = JSON.parseObject(jsonResponse, NodeJsonResponse.class);
                            setNodeLatLng(nodeJsonResponse, node);
                            checkJsonObject(nodeJsonResponse, i);
                        } catch (IOException e) {
                            throw new ThreeServiceException("百度地图服务获取失败");
                        }
                        break;
                    case 2:
                        checkCellNull(i, cell, ExcelColumn.NODE_THREE);
                        int isCenter = (int) cell.getNumericCellValue();
                        if (isCenter <0 || isCenter > 1) {
                            throw new NodeTransferException("第" + i + "行是否是中心点列只能为1或者0");
                        }
                        node.setIsCenter(isCenter);
                        break;
                    default:
                        throw new VehicleTransferException("上传失败");
                }
            }
            nodes.add(node);
        }
        return nodes;
    }

    private void checkCellNull(int row, Cell o, String colnmnName) {
        if (o == null) {
            throw new NodeTransferException("第" + row + "行" + colnmnName + "不能为空");
        }
    }

    private void checkJsonObject(NodeJsonResponse nodeJsonResponse, int row) {
        if (nodeJsonResponse.getResult().getLevel() == "UNKNOWN") {
            throw new NodeTransferException("请检查" + row + "行的详细地址是否正确");
        }
        int statu = nodeJsonResponse.getStatus();
        if (statu == 0){
            return;
        }
        if (statu == 2) {
            throw new NodeTransferException("请检查" + row + "行的详细地址是否正确");
        }else {
            throw new NodeTransferException("请求百度地图API 出现错误");
        }
    }

    private void setNodeLatLng(NodeJsonResponse nodeJsonResponse, NodePojo node){
        double lat = nodeJsonResponse.getResult().getLocation().getLat();
        double lng = nodeJsonResponse.getResult().getLocation().getLng();
        node.setLat(lat);
        node.setLng(lng);
    }

    private boolean matchChinese(String str) {
        String regex = "^[\u4E00-\u9FA5]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
}
