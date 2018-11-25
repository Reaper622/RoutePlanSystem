package com.may.routeplansystem;

import com.may.routeplansystem.dao.VehicleDao;
import com.may.routeplansystem.pojo.VehicleMessage;
import com.may.routeplansystem.service.DistanceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("prod")
public class RouteplansystemApplicationTests {

    private VehicleMessage vehicleMessage;
    @Autowired
    private DistanceService distanceService;

    @Test
    public void test(){
        distanceService.generateDistanceFromNode(1);
        distanceService.updateDisAndTime(1);
    }

}
