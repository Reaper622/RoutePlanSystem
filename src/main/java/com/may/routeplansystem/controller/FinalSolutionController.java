package com.may.routeplansystem.controller;

import com.may.routeplansystem.entity.dto.ResponseEntity;
import com.may.routeplansystem.entity.vo.FinalSolutionVo;
import com.may.routeplansystem.service.FinalSolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("finalSolution")
public class FinalSolutionController {

    @Autowired
    private FinalSolutionService finalSolutionService;

    /**
     * @api {GET} /finalSolution/getAllFinalSolution 得到一个问题的所有解决方案
     * @apiDescription 通过问题Id得到所有该问题的解决方案
     * @apiGroup finalSolution
     * @apiParam {Number} questionId 问题id
     * @apiSuccess {Number} finalSolutionId 方案编号
     * @apiSuccess {String[]} routes 该方案下的所有路径
     * @apiSuccess {Number} totalDis 该方案的总路径
     * @apiSuccess {Number} userChoice 用户选择该方案的标志,0表示没有选择，1表示选择了
     * @apiSuccess {String} createTime 创建方案的时间
     * @apiSuccessExample Success-Response:
     *     HTTP/1.1 200 OK
     *     {
     *         "status":200,
     *         "object": [
     *         {
     *             "finalSolutionId":1,
     *             "routes":[
     *             "第一条路径",
     *             "第二条路径"
     *             ]
     *             “totalDis”:100,
     *             "userChoice":0
     *             "createTime": "2018-10-06 12:00:00"
     *         }
     *         ]
     *     }
     */
    @GetMapping("getAllFinalSolution")
    public ResponseEntity getAllFinalSolution(int questionId){
        List<FinalSolutionVo> finalSolutionVos = finalSolutionService.getAllFinalSolutionOrdered(questionId);
        return new ResponseEntity<>(200, finalSolutionVos);
    }

    /**
     * @api {GET} /finalSolution/getFinalSolution 得到一个方案的所有路径
     * @apiDescription 通过方案Id得到方案下的所有路径
     * @apiGroup finalSolution
     * @apiParam {Number} finalSolutionId 方案id
     * @apiSuccess {Number} finalSolutionId 方案编号
     * @apiSuccess {String[]} routes 该方案下的所有路径
     * @apiSuccess {Number} totalDis 该方案的总路径
     * @apiSuccess {Number} userChoice 用户选择该方案的标志,0表示没有选择，1表示选择了
     * @apiSuccess {String} createTime 创建方案的时间
     * @apiSuccessExample Success-Response:
     *     HTTP/1.1 200 OK
     *     {
     *         "status":200,
     *         "object":{
     *             "finalSolutionId":1,
     *             "routes":[
     *             "第一条路径",
     *             "第二条路径"
     *             ]
     *             “totalDis”:100,
     *             "userChoice":0
     *             "createTime": "2018-11-11 12:00:00"
     *         }
     *     }
     */
    @GetMapping("getFinalSolution")
    public ResponseEntity getFinalSolution(int finalSolutionId){
        FinalSolutionVo finalSolutionVo = finalSolutionService.getFinalSolution(finalSolutionId);
        return new ResponseEntity<>(200, finalSolutionVo);
    }

    /**
     * @api {DELETE} /finalSolution/removeFinalSolution 删除一个方案
     * @apiDescription 通过方案Id删除方案
     * @apiGroup finalSolution
     * @apiParam {Number} finalSolutionId 方案id
     */
    @DeleteMapping("removeFinalSolution")
    public ResponseEntity removeFinalSolution(int finalSolutionId){
        finalSolutionService.removeFinalSolution(finalSolutionId);
        return new ResponseEntity<>(200, null);
    }

    /**
     * @api {DELETE} /finalSolution/removeAllQuestionFinalSolution 删除一个问题的所有解决方案
     * @apiDescription 通过问题id删除所有该问题的解决方案
     * @apiGroup finalSolution
     * @apiParam {Number} questionId 问题id
     */
    @DeleteMapping("removeAllQuestionFinalSolution")
    public ResponseEntity removeAllQuestionFinalSolution(int questionId){
        finalSolutionService.removeAllFinalSolutionByQuestionId(questionId);
        return new ResponseEntity<>(200, null);
    }

    /**
     * @api {GET} /finalSolution/getOneVersionFinalSolution 得到一个版本的解决方案
     * @apiDescription 通过问题Id和版本号得到一个版本的所有解决方案
     * @apiGroup finalSolution
     * @apiParam {Number} questionId 问题id
     * @apiParam {Number} version 版本号
     * @apiSuccess {Number} finalSolutionId 方案编号
     * @apiSuccess {String[]} routes 该方案下的所有路径
     * @apiSuccess {Number} totalDis 该方案的总路径
     * @apiSuccess {Number} userChoice 用户选择该方案的标志,0表示没有选择，1表示选择了
     * @apiSuccess {String} createTime 创建方案的时间
     * @apiSuccessExample Success-Response:
     *     HTTP/1.1 200 OK
     *     {
     *         "status":200,
     *         "object": [
     *         {
     *             "finalSolutionId":1,
     *             "routes":[
     *             "第一条路径",
     *             "第二条路径"
     *             ]
     *             “totalDis”:100,
     *             "userChoice":0
     *             "createTime": "2018-11-11 12:00:00"
     *         }
     *         ]
     *     }
     */
    @GetMapping("getOneVersionFinalSolution")
    public ResponseEntity getOneVersionFinalSolution(int questionId, int version){
        List<FinalSolutionVo> finalSolutionVos =
                finalSolutionService.getOneVersionFinalSolution(questionId, version);
        return new ResponseEntity<>(200, finalSolutionVos);
    }

    /**
     * @api {GET} /finalSolution/getAllVersion 得到该问题的所有版本号
     * @apiDescription 通过问题Id得到该问题的所有版本号
     * @apiGroup finalSolution
     * @apiParam {Number} questionId 问题id
     * @apiSuccessExample Success-Response:
     *     HTTP/1.1 200 OK
     *     {
     *         “status”：200
     *         “Object”: [
     *              1,
     *              2
     *         ]
     *     }
     */
    @GetMapping("getAllVersion")
    public ResponseEntity getAllVersion(int questionId){
        List<Integer> integers = finalSolutionService.getAllVersion(questionId);
        return new ResponseEntity<>(200, integers);
    }

    /**
     * @api {PATCH} /finalSolution/updateFinalSolutionState 修改用户选择的方案的状态
     * @apiDescription 用户选择一个自己觉得最好的方案,一个问题只能选择一个
     * @apiGroup finalSolution
     * @apiParam {Number} questionId 问题id
     */
    @PatchMapping("updateFinalSolutionState")
    public ResponseEntity updateFinalSolutionState(int finalSolutionId){
        finalSolutionService.updateFinalSolutionState(finalSolutionId);
        return new ResponseEntity<>(200, null);
    }

}
