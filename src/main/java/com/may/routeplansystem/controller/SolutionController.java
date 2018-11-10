package com.may.routeplansystem.controller;

import com.may.routeplansystem.entity.dto.ResponseEntity;
import com.may.routeplansystem.entity.vo.SolutionVo;
import com.may.routeplansystem.service.SolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author May
 */

@RestController
@RequestMapping("solution")
public class SolutionController {

    @Autowired
    private SolutionService solutionService;

    /**
     * @api {GET} /solution/getBetterTimeSolution 得到一个问题两个时间和距离最优解决方案
     * @apiDescription 通过问题Id在所用版本解决方案中得到一个问题两个时间和距离最优解决方案，isTime 0标识距离最优 1标识时间最优
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
    public ResponseEntity getBetterSolution(int questionId){
        List<SolutionVo> solutionVos = solutionService.getBetterSolution(questionId);
        return new ResponseEntity<>(200, solutionVos);
    }


    /**
     * @api {GET} /solution/attainSolution 进行得到最优方案算法
     * @apiDescription 通过问题Id进行得到最优方案算法
     * @apiGroup Solution
     * @apiParam {Number} questionId 问题ID
     */
    @GetMapping("attainSolution")
    public ResponseEntity attainSolution(int questionId){
        return new ResponseEntity<>(200, null);
    }

    /**
     * @api {DELETE} /solution/removeAllSolution 删除一个问题的所有解决方案
     * @apiDescription 通过问题Id删除一个问题的所有解决方案
     * @apiGroup Solution
     * @apiParam {Number} questionId 问题ID
     */
    @DeleteMapping("removeAllSolution")
    public ResponseEntity removeAllSolution(int questionId){
        solutionService.removeAllSolution(questionId);
        return new ResponseEntity<>(200, null);
    }

    /**
     * @api {DELETE} /solution/removeAllSolution 删除一个问题的一个版本的所有方案
     * @apiDescription 通过问题Id删除一个问题的一个版本的所有方案
     * @apiGroup Solution
     * @apiParam {Number} questionId 问题ID
     * @apiParam {Number} versionId 版本ID
     */
    @DeleteMapping("removeOneVersionSolution")
    public ResponseEntity removeOneVersionSolution(int questionId, int versionId){
        solutionService.removeOneVersionSolution(questionId, versionId);
        return new ResponseEntity<>(200, null);
    }

}
