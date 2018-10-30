package com.may.routeplansystem.service;

import com.may.routeplansystem.entity.po.Distance;

public interface DistanceService {

    /**
     * 得到一个dis和time都为0的distance
     * @return
     */
    public Distance getUpdateDistance(int questionId);

    /**
     * 修改一个distance的时间和距离
     * @param distance
     */
    public void updateDisAndTime(Distance distance);
}
