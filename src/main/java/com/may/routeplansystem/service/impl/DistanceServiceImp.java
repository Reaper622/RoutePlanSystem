package com.may.routeplansystem.service.impl;

import com.alibaba.fastjson.JSON;
import com.may.routeplansystem.constant.ExceptionMessage;
import com.may.routeplansystem.constant.ProcessState;
import com.may.routeplansystem.dao.DistanceDao;
import com.may.routeplansystem.dao.NodeDao;
import com.may.routeplansystem.dao.QuestionDao;
import com.may.routeplansystem.entity.po.Distance;
import com.may.routeplansystem.entity.po.NodePojo;
import com.may.routeplansystem.entity.po.Question;
import com.may.routeplansystem.entity.vo.DistanceJsonResponse;
import com.may.routeplansystem.exception.ProcessException;
import com.may.routeplansystem.exception.ThreeServiceException;
import com.may.routeplansystem.service.DistanceService;
import com.may.routeplansystem.service.util.ServiceUtil;
import com.may.routeplansystem.util.NetWorkUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
public class DistanceServiceImp implements DistanceService {

    @Autowired
    private NodeDao nodeDao;

    @Autowired
    private DistanceDao distanceDao;

    @Autowired
    private QuestionDao questionDao;

    @Override
    public void updateDisAndTime(int questionId) {
        List<Distance> distances = distanceDao.findUpdateDistances(questionId);
        distances.forEach(distance -> {
            getDistanceTimeAndDis(distance);
            ServiceUtil.checkSqlExecuted(distanceDao.updateDisAndTime(distance));
        });
    }

    @Override
    public Distance getDistanceTimeAndDis(Distance distance) {
        return setTimeAndDis(distance);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void generateDistanceFromNode(int questionId) {
        Question question = questionDao.findQuestionByQuestionId(questionId);
        checkQuestion(question);
        List<NodePojo> nodes = nodeDao.selectAllNodes(questionId);
        checkNodesExist(nodes);
        nodes.forEach(node -> nodes.forEach(innerNode -> {
            if (node != innerNode) {
                Distance distance = new Distance();
                distance.setStartNodeId(node.getNodeId());
                distance.setEndNodeId(innerNode.getNodeId());
                distance.setQuestionId(node.getQuestionId());
                setTimeAndDis(distance);
                distanceDao.insertDis(distance);
            }
        }));
        questionDao.updateQuestionProcessState(ProcessState.CALCULATE_DISTANCE, questionId);
    }

    private void checkQuestion(Question question) {
        if (question == null) {
            throw new ProcessException("请先创建问题");
        }
        if (question.getProcessState() < ProcessState.LOAD_VEHICLE) {
            throw new ProcessException("请先导入车辆");
        }
    }

    private void checkNodesExist(List<NodePojo> nodePojos) {
        if (nodePojos.isEmpty()) {
            throw new ProcessException(ExceptionMessage.NOT_NODES);
        }
    }

    private Distance setTimeAndDis(Distance distance) {
        String responseStr = getParseStr(distance);
        DistanceJsonResponse response = JSON.parseObject(responseStr, DistanceJsonResponse.class);
        distance.setDis(response.getResult().get(0).getDistance().getValue());
        distance.setTime(response.getResult().get(0).getDuration().getValue());
        return distance;
    }

    private String getParseStr(Distance distance) {
        try {
            NodePojo startNode = nodeDao.selectNodeByNodeId(distance.getStartNodeId());
            NodePojo endNode = nodeDao.selectNodeByNodeId(distance.getEndNodeId());
            String startNodePositionStr = startNode.getLat() + "," + startNode.getLng();
            String endNodePostionStr = endNode.getLat() + "," + endNode.getLng();
            String url = "http://api.map.baidu.com/routematrix/v2/driving?output=json&origins=" + startNodePositionStr +
                    "&destinations=" + endNodePostionStr + "&ak=T558r6l2fgG7DNIItH0GVHLpC96KP770";
            return NetWorkUtil.visitUrl(url);
        } catch (IOException e) {
            throw new ThreeServiceException("通过百度地图API 获取两点之间的距离失败");
        }
    }
}
