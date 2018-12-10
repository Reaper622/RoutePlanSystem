package com.may.routeplansystem.service.impl;

import com.may.routeplansystem.dao.FinalSolutionDao;
import com.may.routeplansystem.dao.SolutionDao;
import com.may.routeplansystem.entity.po.FinalSolution;
import com.may.routeplansystem.entity.po.Solution;
import com.may.routeplansystem.entity.vo.FinalSolutionVo;
import com.may.routeplansystem.entity.vo.NodeVo;
import com.may.routeplansystem.exception.FinalSolutionUserChoiceException;
import com.may.routeplansystem.service.FinalSolutionService;
import com.may.routeplansystem.util.ServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class FinalSolutionServiceImp implements FinalSolutionService {

    @Autowired
    private FinalSolutionDao finalSolutionDao;

    @Autowired
    private SolutionDao solutionDao;

    @Override
    public FinalSolutionVo getFinalSolution(int finalSolutionId) {
        List<Solution> solutions = solutionDao.findSolutions(finalSolutionId);
        return solutionsToFinalSolutionVo(solutions, finalSolutionId);
    }

    private FinalSolutionVo solutionsToFinalSolutionVo(List<Solution> solutions, int finalSolutionId){
        FinalSolutionVo finalSolutionVo = new FinalSolutionVo();
        List<List<NodeVo>> routes = new LinkedList<>();
        solutions.forEach(solution -> routes.add(parseStringToNodeVos(solution.getRoute())));
        finalSolutionVo.setRoutes(routes);
        finalSolutionVo.setFinalSolutionId(finalSolutionId);
        FinalSolution finalSolution = finalSolutionDao.findFinalSolutionByFinalSolutionId(finalSolutionId);
        finalSolutionVo.setTotalDis(finalSolution.getTotalDis());
        finalSolutionVo.setCreateTime(finalSolution.getCreateTime());
        finalSolutionVo.setUserChoice(finalSolution.getUserChoice());
        return finalSolutionVo;
    }

    public List<NodeVo> parseStringToNodeVos(String str){
        List<NodeVo> nodeVos = new LinkedList<>();
        String[] nodesStr = str.split(";");
        for (String nodeStr : nodesStr) {
            String[] concretNode = nodeStr.split(",");
            NodeVo nodeVo = new NodeVo();
            nodeVo.setLat(Double.valueOf(concretNode[1]));
            nodeVo.setLng(Double.valueOf(concretNode[0]));
            nodeVo.setNodeAddr(concretNode[2]);
            nodeVos.add(nodeVo);
        }
        return nodeVos;
    }

    @Override
    public List<FinalSolutionVo> getAllFinalSolutionOrdered(int questionId) {
        List<FinalSolutionVo> finalSolutionVos = new LinkedList<>();
        List<Integer> finalSolutionIds = finalSolutionDao.findAllFinalSolutionOrdered(questionId);
        finalSolutionIds.forEach(finalSolutionId -> {
            List<Solution> solutions = solutionDao.findSolutions(finalSolutionId);
            FinalSolutionVo finalSolutionVo = solutionsToFinalSolutionVo(solutions, finalSolutionId);
            finalSolutionVos.add(finalSolutionVo);
        });
        return finalSolutionVos;
    }

    @Override
    public void removeFinalSolution(int finalSolutionId) {
        finalSolutionDao.deleteFinalSolution(finalSolutionId);
        solutionDao.deleteSolutionByFinalSolutionId(finalSolutionId);
    }

    @Override
    public void removeAllFinalSolutionByQuestionId(int questionId) {
        List<Integer> finalSolutionIds = finalSolutionDao.findAllFinalSolutionId(questionId);
        finalSolutionIds.forEach(this::removeFinalSolution);
    }

    @Override
    public List<FinalSolutionVo> getOneVersionFinalSolution(int questionId, int version) {
        List<FinalSolutionVo> finalSolutionVos = new LinkedList<>();
        List<Integer> integers = finalSolutionDao.findDifferentVersionFinalSolution(questionId, version);
        integers.forEach(integer -> {
            List<Solution> solutions = solutionDao.findSolutions(integer);
            FinalSolutionVo finalSolutionVo = solutionsToFinalSolutionVo(solutions, integer);
            finalSolutionVos.add(finalSolutionVo);
        });
        return finalSolutionVos;
    }

    @Override
    public int getMaxVersionOfFinalSolution(int questionId) {
        Integer maxVersion = finalSolutionDao.findMaxVersion(questionId);
        Optional<Integer> optional = Optional.ofNullable(maxVersion);
        return optional.orElse(0);
    }

    @Override
    public List<Integer> getAllVersion(int questionId) {
        return finalSolutionDao.findAllVersion(questionId);
    }

    @Override
    public void updateFinalSolutionState(int finalSolutionId) {
        FinalSolution finalSolution = finalSolutionDao.findFinalSolutionByFinalSolutionId(finalSolutionId);
        if (finalSolution.getUserChoice() == 1){
            finalSolutionDao.updateFinalSolutionUserChoice(finalSolutionId);
        }else {
            int num = finalSolutionDao.findNumOfUserChoice(finalSolution.getQuestionId());
            if (num == 1){
                throw new FinalSolutionUserChoiceException("已经选择了一个最优方案");
            }
        }
    }
}
