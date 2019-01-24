package com.may.routeplansystem.service.impl;

import com.may.routeplansystem.dao.QuestionDao;
import com.may.routeplansystem.entity.po.Question;
import com.may.routeplansystem.service.FinalSolutionService;
import com.may.routeplansystem.service.QuestionService;
import com.may.routeplansystem.service.util.ServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 10587
 */
@Service
public class QuestionServiceImp implements QuestionService {

    @Autowired
    private QuestionDao questionDao;

    @Autowired
    private FinalSolutionService finalSolutionService;

    @Override
    public int insertQuestion(Question question) {
        questionDao.insertQuestion(question);
        return question.getQuestionId();
    }

    @Override
    public List<Question> getQuestions(int userId) {
        return questionDao.findQuestionsByUserId(userId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeQuestion(int questionId) {
        finalSolutionService.removeAllFinalSolutionByQuestionId(questionId);
        ServiceUtil.checkSqlExecuted(questionDao.deleteQuestion(questionId));
    }

    @Override
    public void updateQuestion(Question question) {
        ServiceUtil.checkSqlExecuted(questionDao.updateQuestion(question));
    }
}
