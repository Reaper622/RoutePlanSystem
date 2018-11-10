package com.may.routeplansystem.dao;

import com.may.routeplansystem.entity.po.FinalSolution;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FinalSolutionDao {

    boolean insertFinalSolution(FinalSolution finalSolution);

    boolean deleteFinalSolution(int finalSolutionId);

    /**
     * 得到现在方案的版本号
     * @param questionId
     * @return
     */
    int findMaxVersion(int questionId);

    /**
     * 找到所有版本中最好的四个方案
     * @param questionId
     * @return 有序的方案
     */
    List<Integer> findAllFinalSolutionOrdered(int questionId);

    /**
     * 查找到所有属于问题的解决方案的id
     * @param questionId
     * @return
     */
    List<Integer> findAllFinalSolutionId(int questionId);

    /**
     * 找到一个问题的一个版本的解决方案
     * @param questionId 问题id
     * @param version 版本
     * @return
     */
    List<Integer> findDifferentVersionFinalSolution(@Param("questionId") int questionId,
                                                    @Param("version") int version);

    /**
     * 通过finalSolutionId找到方案
     * @param finalSolutionId
     * @return
     */
    FinalSolution findFinalSolutionByFinalSolutionId(int finalSolutionId);

}
