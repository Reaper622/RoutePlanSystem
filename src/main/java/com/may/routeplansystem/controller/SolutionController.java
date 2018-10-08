package com.may.routeplansystem.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author May
 */

@RestController
@RequestMapping("solution")
public class SolutionController {

    /**
     * @api {GET} /solution/getSolutions 得到一个问题的所有版本解决方案
     * @apiDescription 通过问题Id得到所有版本的解决方案
     * @apiGroup Solution
     * @apiParam {Number} questionId 问题ID
     * @apiSuccessExample Success-Response:
     *     HTTP/1.1 200 OK
     *     {
     *       "solutionId": 1,
     *       "questionId": 1,
     *       "route": "方案的具体线路",
     *       "totalDis": 123456,
     *       "totalTime": 123456,
     *       "version": 1,
     *       "delFlag": 0
     *     }
     */
    @GetMapping("getSolutions")
    public void getSolutions(int questionId){}

    /**
     * @api {GET} /solution/getBetterTimeSolution 得到一个问题两个时间最优解决方案
     * @apiDescription 通过问题Id在所用版本解决方案中得到一个问题两个时间最优解决方案
     * @apiGroup Solution
     * @apiParam {Number} questionId 问题ID
     * @apiSuccessExample Success-Response:
     *     HTTP/1.1 200 OK
     *     {
     *       "solutionId": 1,
     *       "questionId": 1,
     *       "route": "方案的具体线路",
     *       "totalDis": 123456,
     *       "totalTime": 123456,
     *       "version": 1,
     *       "delFlag": 0
     *     }
     */
    @GetMapping("getBetterTimeSolution")
    public void getBetterTimeSolution(int questionId){}

    /**
     * @api {GET} /solution/getBetterDistanceSolution 得到一个问题两个距离最优解决方案
     * @apiDescription 通过问题Id在所用版本解决方案中得到一个问题两个距离最优解决方案
     * @apiGroup Solution
     * @apiParam {Number} questionId 问题ID
     * @apiSuccessExample Success-Response:
     *     HTTP/1.1 200 OK
     *     {
     *       "solutionId": 1,
     *       "questionId": 1,
     *       "route": "方案的具体线路",
     *       "totalDis": 123456,
     *       "totalTime": 123456,
     *       "version": 1,
     *       "delFlag": 0
     *     }
     */
    @GetMapping("getBetterDistanceSolution")
    public void getBetterDistanceSolution(int questionId){}

    /**
     * @api {GET} /solution/attainSolution 进行得到最优方案算法
     * @apiDescription 通过问题Id进行得到最优方案算法
     * @apiGroup Solution
     * @apiParam {Number} questionId 问题ID
     */
    @GetMapping("attainSolution")
    public void attainSolution(int questionId){}

    /**
     * @api {DELETE} /solution/removeAllSolution 删除一个问题的所有解决方案
     * @apiDescription 通过问题Id删除一个问题的所有解决方案
     * @apiGroup Solution
     * @apiParam {Number} questionId 问题ID
     */
    @DeleteMapping("removeAllSolution")
    public void removeAllSolution(int questionId){}

    /**
     * @api {DELETE} /solution/removeAllSolution 删除一个问题的一个版本的所有方案
     * @apiDescription 通过问题Id删除一个问题的一个版本的所有方案
     * @apiGroup Solution
     * @apiParam {Number} questionId 问题ID
     * @apiParam {Number} versionId 版本ID
     */
    @DeleteMapping("removeOneVersionSolution")
    public void removeOneVersionSolution(int questionId, int versionId){}
}
