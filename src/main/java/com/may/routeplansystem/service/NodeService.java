package com.may.routeplansystem.service;import com.may.routeplansystem.entity.po.NodePojo;import org.springframework.stereotype.Service;import org.springframework.web.multipart.MultipartFile;import javax.servlet.http.HttpServletRequest;import java.util.List;/** * @author:dengsiyuan * @Date:2018/10/22 16:27 */@Servicepublic interface NodeService {    /**     * 点的excel批量导入     * @param mFile     * @param request     * @param questionId     */    void nodeBatchImport(MultipartFile mFile, HttpServletRequest request, int questionId);    /**     * 通过点击地图导入点信息     *     * @param nodePojo     * @return -1 失败     */    void insertNode(NodePojo nodePojo);    /**     * 删除点     *     * @param nodePojo     * @return 1:删除成功 2: 删除失败 0: 异常     */    void deleteNode(NodePojo nodePojo);    /**     * 更新点     *     * @param nodePojo     * @return 1:删除成功 2: 删除失败 0: 异常     */    void updateNode(NodePojo nodePojo);    /**     * 得到所有问题的点     * @param questionId     * @return     */    List<NodePojo> getQuestionNodes(int questionId);}