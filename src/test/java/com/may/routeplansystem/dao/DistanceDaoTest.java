package com.may.routeplansystem.dao;

import com.may.routeplansystem.entity.po.Distance;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
@Transactional
@Rollback()
public class DistanceDaoTest {

    @Autowired
    private DistanceDao distanceDao;
    private Distance distance;

    @Before
    public void before(){
        distance = new Distance(2, 2, 2, 2,
                1234, 1235);
    }

    @Test
    public void updateDisAndTime() {
        Distance distance1 = new Distance();
        distance1.setDis(1313);
        distance1.setTime(1515);
        distance1.setDistanceId(1);
        boolean flag = distanceDao.updateDisAndTime(distance1);
        assertTrue(flag);
    }

    @Test
    public void findUpdateDistance() {
        List<Distance> distances = distanceDao.findUpdateDistances(3);
        assertEquals(1, distances.size());
    }


    @Test
    public void insertDis() {
        boolean flag = distanceDao.insertDis(distance);
        assertTrue(flag);
    }
}