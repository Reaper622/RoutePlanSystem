package com.may.routeplansystem.controller;

import com.may.routeplansystem.entity.po.Distance;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("distance")
public class DistanceController {

    /**
     * @api {PATCH} /distance/updateDisAndTime 设置两点的距离和时间
     * @apiDescription 通过 distanceId 来修改 distance 和 time
     * @apiGroup Distance
     * @apiParam {Number} distanceId 距离对象ID
     * @apiParam {Number} dis 两地距离间隔
     * @apiParam {Number} time 两地时间间隔
     */
    @PatchMapping("updateDisAndTime")
    public void updateDisAndTime(Distance distance){}

    /**
     * @api {GET} /distance/getUpdateDistance 得到需要设置距离和时间的两点
     * @apiDescription 通过向后台访问此接口前端得到数据来进行计算距离和时间
     * @apiGroup Distance
     * @apiSuccessExample Success-Response:
     *     HTTP/1.1 200 OK
     *     {
     *       "distanceId": 1,
     *       "questionId": 1,
     *       "startNodeId": 1,
     *       "endNodeId": 1,
     *       "startNodeAddr":"起始地点位置",
     *       "endNodeAddr":"终点地点位置"
     *     }
     */
    @GetMapping("getUpdateDistance")
    public void getUpdateDistance(){}
}
