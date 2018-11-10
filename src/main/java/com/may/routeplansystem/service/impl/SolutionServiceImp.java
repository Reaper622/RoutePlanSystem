package com.may.routeplansystem.service.impl;

import com.may.routeplansystem.dao.SolutionDao;
import com.may.routeplansystem.entity.po.Solution;
import com.may.routeplansystem.entity.vo.SolutionVo;
import com.may.routeplansystem.service.SolutionService;
import com.may.routeplansystem.util.ServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class SolutionServiceImp implements SolutionService {

    @Autowired
    private SolutionDao solutionDao;

    @Override
    public List<SolutionVo> getBetterSolution(int questionId) {
        List<Solution> betterTimeSolution = solutionDao.findBetterTimeSolutions(questionId);
        List<Solution> betterDisSolution = solutionDao.findBetterDistanceSolutions(questionId);
        List<SolutionVo> betterTimeSolutioVo = solutionsToSolutionVos(betterTimeSolution, true);
        List<SolutionVo> betterDisSolutionVo = solutionsToSolutionVos(betterDisSolution, false);

        betterDisSolutionVo.addAll(betterTimeSolutioVo);
        return betterDisSolutionVo;
    }

    private List<SolutionVo> solutionsToSolutionVos(List<Solution> solutions, boolean isTime){
        List<SolutionVo> solutionVos = new LinkedList<>();
        solutions.forEach(solution -> {
            SolutionVo solutionVo = new SolutionVo();
            solutionVo.setSolutionId(solution.getSolutionId());
            solutionVo.setQuestionId(solution.getQuestionId());
            solutionVo.setRoute(solution.getRoute());
            solutionVo.setTotalDis(solution.getTotalDis());
            solutionVo.setTotalTime(solution.getTotalTime());
            solutionVo.setVersion(solution.getVersion());
            solutionVo.setDelFlag(0);
            if (isTime){
                solutionVo.setIsTime(1);
            }else {
                solutionVo.setIsTime(0);
            }
            solutionVos.add(solutionVo);
        });
        return solutionVos;
    }

    @Override
    public void removeAllSolution(int questionId) {
        ServiceUtil.checkSqlExecuted(solutionDao.deleteAllSolutions(questionId));
    }

    @Override
    public void removeOneVersionSolution(int questionId, int version) {
        ServiceUtil.checkSqlExecuted(solutionDao.deleteOneVersionSolutions(questionId, version));
    }
}
