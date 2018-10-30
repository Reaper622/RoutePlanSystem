package com.may.routeplansystem.service.impl;

import com.may.routeplansystem.dao.DistanceDao;
import com.may.routeplansystem.entity.po.Distance;
import com.may.routeplansystem.service.DistanceService;
import com.may.routeplansystem.util.ServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DistanceServiceImp implements DistanceService {

    @Autowired
    private DistanceDao distanceDao;

    @Override
    public Distance getUpdateDistance(int questionId) {
        return distanceDao.findUpdateDistances(questionId);
    }

    @Override
    public void updateDisAndTime(Distance distance) {
        ServiceUtil.checkSqlExecuted(distanceDao.updateDisAndTime(distance));
    }
}
