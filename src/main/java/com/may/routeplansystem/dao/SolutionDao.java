package com.may.routeplansystem.dao;

import com.may.routeplansystem.entity.po.Solution;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author May
 */
@Repository
public interface SolutionDao {

    /**
     * 得到一个方案的所有路径
     *
     * @param finalSolutionId 方案编号
     * @return
     */
    List<Solution> findSolutions(int finalSolutionId);

    boolean insertSolution(Solution solution);

    boolean deleteSolution(int solutionId);

    boolean deleteSolutionByFinalSolutionId(int finalSolutionId);
}
