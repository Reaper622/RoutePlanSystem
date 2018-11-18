package com.may.routeplansystem.service;

import com.may.routeplansystem.entity.po.Distance;

import java.io.IOException;
import java.util.List;

public interface DistanceService {

    /**
     * 赋值所有没有赋值时间和距离的distance
     */
    void updateDisAndTime(int questionId);

    /**
     * 通过百度Api获得两点的时间和距离
     * @param distance
     * @return
     */
    Distance getDistanceTimeAndDis(Distance distance);

    /**
     * 单纯的生成没有时间和距离的distance
     * @param questionId
     */
    void generateDistanceFromNode(int questionId);
}
