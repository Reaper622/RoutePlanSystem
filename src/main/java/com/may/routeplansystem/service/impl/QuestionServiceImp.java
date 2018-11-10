package com.may.routeplansystem.service.impl;

import com.may.routeplansystem.dao.QuestionDao;
import com.may.routeplansystem.entity.po.Question;
import com.may.routeplansystem.service.QuestionService;
import com.may.routeplansystem.util.ServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImp implements QuestionService {

    @Autowired
    private QuestionDao questionDao;

    @Override
    public void insertQuestion(Question question) {
        ServiceUtil.checkSqlExecuted(questionDao.insertQuestion(question));
    }

    @Override
    public List<Question> getQuestions(int userId) {
        return questionDao.findQuestionsByUserId(userId);
    }

    @Override
    public void removeQuestion(int questionId) {
        ServiceUtil.checkSqlExecuted(questionDao.deleteQuestion(questionId));
    }

    @Override
    public void updateQuestion(Question question) {
        ServiceUtil.checkSqlExecuted(questionDao.updateQuestion(question));
    }
}
