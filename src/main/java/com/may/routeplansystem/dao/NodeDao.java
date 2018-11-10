package com.may.routeplansystem.dao;import com.may.routeplansystem.pojo.NodePojo;import org.springframework.stereotype.Repository;import java.util.List;/** * @author:dengsiyuan * @Date:2018/10/29 21:12 */@Repository("nodeDao")public interface NodeDao {    /**     * 导入点的信息     * @param nodePojo     * @return -1 插入失败     * */    int insertNode(NodePojo nodePojo);    /**     * 通过nodeId删除点     * @param nodeId     * @return true/false     * */    boolean deleteNodeByNodeId(int nodeId);    /**     * 通过问题编号删除点     * @param questionId     * @return true/false     * */    boolean deleteNodeByQuestionId(int questionId);    /**     * 通过nodeId更新点的信息     * @param nodePojo     * @return true     * */    boolean updateNodeByNodeId(NodePojo nodePojo);    /**     * 查询点的信息     * @param nodeId     * @return nodePojo     * */    NodePojo selectNodeByNodeId(int nodeId);    /**     * 通过点的name查询点的信息     * @param nodeName     * @return nodePojo     * */    NodePojo selectNodeByNodeName(String nodeName);    //    下面部分是梅勇杰加入的方法    List<NodePojo> selectCenterNode(int questionId);    List<NodePojo> selectServiceNode(int questionId);}