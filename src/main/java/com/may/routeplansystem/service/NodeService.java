package com.may.routeplansystem.service;import com.may.routeplansystem.pojo.NodePojo;import org.apache.http.client.methods.HttpGet;import org.apache.poi.ss.usermodel.Workbook;import org.springframework.stereotype.Service;import org.springframework.web.multipart.MultipartFile;import javax.servlet.http.HttpServletRequest;import javax.servlet.http.HttpSession;import java.io.File;/** * @author:dengsiyuan * @Date:2018/10/22 16:27 */@Servicepublic interface NodeService {    /**     * 判断文件类型     * @param fileName     * @param mFile     * @param request     * @return 返回文件判断结果     * */    String batchImport(String fileName, MultipartFile mFile, HttpServletRequest request, String userId, int questionId);    /**     * 导入数据，并返回结果     * @param wb     * @param tempFile     * @return 返回具体导入结果     * */    String readExcel(Workbook wb, File tempFile, int questionId);    /**     * 通过点击地图导入点信息     * @param nodePojo     * @return -1 失败     * */    int insertNode(NodePojo nodePojo);    /**     * 删除点     * @param nodePojo     * @return 1:删除成功 2: 删除失败 0: 异常     * */    int deleteNode(NodePojo nodePojo);    /**     * 更新点     * @param nodePojo     * @return 1:删除成功 2: 删除失败 0: 异常     * */    int updateNode(NodePojo nodePojo);    /**     * 查询点的信息     * @param nodePojo     * @return nodePojo     * */    Object selectNode(NodePojo nodePojo);}