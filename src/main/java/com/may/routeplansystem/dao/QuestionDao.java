package com.may.routeplansystem.dao;

import com.may.routeplansystem.entity.po.Question;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 10587
 */
@Repository
public interface QuestionDao {

    boolean insertQuestion(Question question);

    boolean deleteQuestion(int questionId);

    boolean updateQuestion(Question question);

    List<Question> findQuestionsByUserId(int userId);

    Question findQuestionByQuestionId(int questionId);
}
