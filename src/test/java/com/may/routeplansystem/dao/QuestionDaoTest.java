package com.may.routeplansystem.dao;

import com.may.routeplansystem.entity.po.Question;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
@Transactional
@Rollback()
public class QuestionDaoTest {

    @Autowired
    private QuestionDao questionDao;
    private Question question;

    @Before
    public void before(){
        question = new Question(2, "question2", 2, 0);
    }

    @Test
    public void insertQuestion() {
        boolean flag = questionDao.insertQuestion(question);
        assertTrue(flag);
    }

    @Test
    public void deleteQuestion() {
        boolean flag = questionDao.deleteQuestion(1);
        assertTrue(flag);
    }

    @Test
    public void updateQuestion() {
        Question question = new Question(1,"问题",1 ,0);
        questionDao.updateQuestion(question);
        Question question1 = questionDao.findQuestionByQuestionId(1);
        assertEquals("问题", question1.getQuestionName());
    }

    @Test
    public void findQuestionsByUserId() {
        List<Question> questions = questionDao.findQuestionsByUserId(1);
        assertEquals(1, questions.size());
    }
}