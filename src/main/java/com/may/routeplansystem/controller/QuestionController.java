package com.may.routeplansystem.controller;

import com.may.routeplansystem.entity.po.Question;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("question")
public class QuestionController {

    /**
     * @api {POST} /question/insertQuestion 添加问题
     * @apiDescription 通过question详细信息添加question
     * @apiGroup Question
     * @apiParam {String} questionName 问题名称
     * @apiParam {Number} userId 用户Id
     */
    @PostMapping("insertQuestion")
    public void insertQuestion(Question question){}

    /**
     * @api {GET} /question/getQuestions 得到questions
     * @apiDescription 通过用户Id得到所用该用户的问题
     * @apiGroup Question
     * @apiParam {Number} userId 用户Id
     * @apiSuccessExample Success-Response:
     *     HTTP/1.1 200 OK
     *     {
     *       "questionId": 1,
     *       "questionName": "问题名称",
     *       "userId": 1,
     *       "del_flag": 0
     *     }
     */
    @GetMapping("getQuestions")
    public void getQuestions(int userId){}

    /**
     * @api {DELETE} /question/removeQuestion 删除问题
     * @apiDescription 通过questionId删除问题
     * @apiGroup Question
     * @apiParam {Number} questionId 问题ID
     */
    @DeleteMapping("removeQuestion")
    public void removeQuestion(int questionId){}

    /**
     * @api {PATCH} /question/updateQuestion 修改问题
     * @apiDescription 通过questionId修改问题的名称
     * @apiGroup Question
     * @apiParam {Number} questionId 问题ID
     * @apiParam {String} questionName 问题名称
     */
    @PatchMapping("updateQuestion")
    public void updateQuestion(Question question){}
}
