package com.may.routeplansystem.service;

import com.may.routeplansystem.entity.po.Distance;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DistanceServiceTest {

    @Autowired
    private DistanceService distanceService;

    @Test
    public void updateDisAndTime() {
        distanceService.updateDisAndTime(1);
    }

    @Test
    public void getDistanceTimeAndDis() {
        Distance distance = new Distance();
        distance.setDistanceId(1);
        distance.setStartNodeId(1);
        distance.setEndNodeId(2);
        distance.setQuestionId(1);
        distanceService.getDistanceTimeAndDis(distance);
    }

    @Test
    public void generateDistanceFromNode() {
        distanceService.generateDistanceFromNode(1);
    }
}