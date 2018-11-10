package com.may.routeplansystem.dao;

import com.may.routeplansystem.entity.po.FinalSolution;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
@Transactional
@Rollback()
public class FinalSolutionDaoTest {

    @Autowired
    private FinalSolutionDao finalSolutionDao;
    private FinalSolution finalSolution;

    public void before(){
        finalSolution = new FinalSolution();
        finalSolution.setFinalSolutionId(3);
        finalSolution.setQuestionId(1);
        finalSolution.setTotalDis(30);
        finalSolution.setVersion(1);
    }

    @Test
    public void insertFinalSolution() {
        boolean flag = finalSolutionDao.insertFinalSolution(finalSolution);
        assertTrue(flag);
    }

    @Test
    public void deleteFinalSolution() {
        boolean flag = finalSolutionDao.deleteFinalSolution(1);
        assertTrue(flag);
    }

    @Test
    public void findMaxVersion() {
        int value = finalSolutionDao.findMaxVersion(1);
        assertEquals(2, value);
    }

    @Test
    public void findAllFinalSolutionOrdered() {
        List<Integer> integers = finalSolutionDao.findAllFinalSolutionOrdered(1);
        assertEquals(new Integer(2), integers.get(0));
    }

    @Test
    public void findAllFinalSolutionId() {
        List<Integer> integers = finalSolutionDao.findAllFinalSolutionId(1);
        assertEquals(3, integers.size());
    }

    @Test
    public void findFinalSolutionByFinalSolutionId(){
        FinalSolution finalSolution = finalSolutionDao.findFinalSolutionByFinalSolutionId(1);
        assertEquals(20, finalSolution.getTotalDis(), 0.1);
    }

    @Test
    public void updateFinalSolutionUserChoice(){
        boolean flag = finalSolutionDao.updateFinalSolutionUserChoice(1);
        assertTrue(flag);
    }

    @Test
    public void findNumOfUserChoice(){
        int num = finalSolutionDao.findNumOfUserChoice(1);
        assertEquals(1, num);
    }
}