package com.may.routeplansystem.controller;

import com.may.routeplansystem.algorithm.Algorithm;
import com.may.routeplansystem.entity.dto.ResponseEntity;
import com.may.routeplansystem.entity.po.Question;
import com.may.routeplansystem.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
@CrossOrigin(origins = "http://47.107.65.249:8080",allowCredentials = "true")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private Algorithm algorithm;

    /**
     * @api {POST} /question/insertQuestion 添加问题
     * @apiDescription 通过question详细信息添加question
     * @apiGroup Question
     * @apiParam {String} questionName 问题名称
     * @apiParam {Number} userId 用户Id
     */
    @PostMapping("insertQuestion")
    public ResponseEntity insertQuestion(Question question){
        questionService.insertQuestion(question);
        return new ResponseEntity<>(200, null);
    }

    /**
     * @api {GET} /question/getQuestions 得到questions
     * @apiDescription 通过用户Id得到所用该用户的问题
     * @apiGroup Question
     * @apiParam {Number} userId 用户Id
     * @apiSuccessExample Success-Response:
     *     HTTP/1.1 200 OK
     *     {
     *         "status": 200,
     *         "object": [
     *            {
     *                "questionId": 1
     *                "questionName: "问题名称"",
     *                "userId": 1,
     *                "delFlag": 0
     *            }
     *         ]
     *     }
     */
    @GetMapping("getQuestions")
    public ResponseEntity getQuestions(int userId){
        List<Question> questions = questionService.getQuestions(userId);
        return new ResponseEntity<>(200, questions);
    }

    /**
     * @api {DELETE} /question/removeQuestion 删除问题
     * @apiDescription 通过questionId删除问题
     * @apiGroup Question
     * @apiParam {Number} questionId 问题ID
     */
    @DeleteMapping("removeQuestion")
    public ResponseEntity removeQuestion(int questionId){
        questionService.removeQuestion(questionId);
        return new ResponseEntity<>(200, null);
    }

    /**
     * @api {PATCH} /question/updateQuestion 修改问题
     * @apiDescription 通过questionId修改问题的名称
     * @apiGroup Question
     * @apiParam {Number} questionId 问题ID
     * @apiParam {String} questionName 问题名称
     */
    @PatchMapping("updateQuestion")
    public ResponseEntity updateQuestion(Question question){
        questionService.updateQuestion(question);
        return new ResponseEntity<>(200, null);
    }

    /**
     * @api {GET} /question/executeAlgorithm 执行算法
     *      * @apiDescription 通过questionId来执行算法
     *      * @apiGroup Question
     *      * @apiParam {Number} questionId 问题ID
     */
    @GetMapping("executeAlgorithm")
    public ResponseEntity executeAlgorithm(int questionId){
        algorithm.executeAlgorithm(questionId);
        return new ResponseEntity<>(200, null);
    }
}
