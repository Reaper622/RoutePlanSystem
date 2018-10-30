package com.may.routeplansystem.dao;

import com.may.routeplansystem.entity.po.Solution;
import org.junit.Before;
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
public class SolutionDaoTest {

    @Autowired
    private SolutionDao solutionDao;
    private Solution solution;

    @Before
    public void before(){
        solution = new Solution(2, 2, "route2",
                3333, 4444, 1, 0);
    }

    @Test
    public void findSolutions() {
        List<Solution> solutionList = solutionDao.findSolutions(1);
        assertEquals(3, solutionList.size());
    }

    @Test
    public void findBetterTimeSolutions() {
       List<Solution> solutions = solutionDao.findBetterTimeSolutions(1);
       assertEquals("route4", solutions.get(0).getRoute());
    }

    @Test
    public void findBetterDistanceSolutions() {
        List<Solution> solutions = solutionDao.findBetterDistanceSolutions(1);
        assertEquals("route1", solutions.get(0).getRoute());
    }

    @Test
    public void insertSolution() {
        boolean flag = solutionDao.insertSolution(solution);
        assertTrue(flag);
    }

    @Test
    public void deleteAllSolutions() {
        boolean flag = solutionDao.deleteAllSolutions(1);
        assertTrue(flag);
    }

    @Test
    public void deleteOneVersionSolutions() {
        boolean flag = solutionDao.deleteOneVersionSolutions(1, 1);
        assertTrue(flag);
    }
}