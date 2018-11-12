package com.may.routeplansystem.service;

import com.may.routeplansystem.entity.po.FinalSolution;
import com.may.routeplansystem.entity.vo.FinalSolutionVo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class FinalSolutionServiceTest {

    @Autowired
    private FinalSolutionService finalSolutionService;

    @Test
    public void getFinalSolution() {
        FinalSolutionVo finalSolutionVo = finalSolutionService.getFinalSolution(1);
        assertEquals(3, finalSolutionVo.getRoutes().size());
    }

    @Test
    public void getAllFinalSolutionOrdered() {
    }

    @Test
    public void removeFinalSolution() {
    }

    @Test
    public void removeAllFinalSolutionByQuestionId() {
    }

    @Test
    public void getOneVersionFinalSolution() {
    }

    @Test
    public void getMaxVersionOfFinalSolution() {
    }
}