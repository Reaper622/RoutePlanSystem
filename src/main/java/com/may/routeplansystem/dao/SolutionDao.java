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

    List<Solution> findSolutions(int questionId);

    List<Solution> findBetterTimeSolutions(int questionId);

    List<Solution> findBetterDistanceSolutions(int questionId);

    boolean insertSolution(Solution solution);

    boolean deleteAllSolutions(int questionId);

    boolean deleteOneVersionSolutions(@Param("questionId") int questionId, @Param("version") int version);
}
