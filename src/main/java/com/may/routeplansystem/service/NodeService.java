package com.may.routeplansystem.service;import org.apache.poi.ss.usermodel.Workbook;import org.springframework.stereotype.Service;import org.springframework.web.multipart.MultipartFile;import javax.servlet.http.HttpSession;import java.io.File;/** * @author:dengsiyuan * @Date:2018/10/22 16:27 */@Servicepublic interface NodeService {    /**     * 判断文件类型     * @param fileName     * @param mFile     * @param session     * @return 返回文件判断结果     * */    String batchImport(String fileName, MultipartFile mFile, HttpSession session);    /**     * 导入数据，并返回结果     * @param wb     * @param tempFile     * @return 返回具体导入结果     * */    String readExcel(Workbook wb, File tempFile);}