package com.may.routeplansystem.service;

import com.may.routeplansystem.entity.po.Question;

import java.util.List;

public interface QuestionService {

    /**
     * 添加问题
     */
    int insertQuestion(Question question);

    /**
     * 得到一个用户的所有问题，包括执行过算法和没有执行过算法
     * @param userId 用户Id
     * @return
     */
    List<Question> getQuestions(int userId);

    /**
     * 删除问题
     */
    void removeQuestion(int questionId);

    /**
     * 修改问题，其实就是修改问题的名称
     */
    void updateQuestion(Question question);
}
