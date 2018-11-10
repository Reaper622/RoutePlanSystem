package com.may.routeplansystem.service;

import com.may.routeplansystem.entity.vo.SolutionVo;

import java.util.List;

public interface SolutionService {

    /**
     * 分别取得时间和距离最好的方案
     * @param questionId
     * @return
     */
    public List<SolutionVo> getBetterSolution(int questionId);

    /**
     * 删除一个问题的所有解决方案
     * @param questionId
     */
    public void removeAllSolution(int questionId);

    /**
     * 删除一个问题的一个版本的所有方案
     * @param questionId
     * @param version
     */
    public void removeOneVersionSolution(int questionId, int version);

}
