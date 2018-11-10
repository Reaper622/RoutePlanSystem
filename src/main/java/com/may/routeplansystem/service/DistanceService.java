package com.may.routeplansystem.service;

import com.may.routeplansystem.entity.po.Distance;

import java.io.IOException;
import java.util.List;

public interface DistanceService {

    /**
     * 赋值所有没有赋值时间和距离的distance
     */
    public void updateDisAndTime(int questionId);

    /**
     * 通过百度Api获得两点的时间和距离
     * @param distance
     * @return
     */
    public Distance getDistanceTimeAndDis(Distance distance);

    public void generateDistanceFromNode(int questionId);
}
