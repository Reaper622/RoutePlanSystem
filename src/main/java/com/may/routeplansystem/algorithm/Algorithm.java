package com.may.routeplansystem.algorithm;

import com.may.routeplansystem.constant.ProcessState;
import com.may.routeplansystem.entity.po.Question;
import com.may.routeplansystem.exception.ProcessException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

/**
 * @author 10587
 */
public abstract class Algorithm implements AlgorithmExecutor {

    /**
     * 在执行算法之前执行的方法
     * @param questionId
     */
    protected abstract void beforeExecute(int questionId);

    /**
     * 执行算法
     * @param questionId
     */
    protected abstract void executeAlgorithm(int questionId);

    /**
     * 在执行算法之后执行的方法
     * @param questionId
     */
    protected abstract void afterExecute(int questionId);

    @Override
    public void execute(int questionId){
        beforeExecute(questionId);
        executeAlgorithm(questionId);
        afterExecute(questionId);
    }

    protected void generalBeforeExecute(Question question) {
        checkProcessState(question);
    }

    private void checkProcessState(Question question) {
        if (question == null) {
            throw new ProcessException("请先创建问题");
        }
        if (question.getProcessState() < ProcessState.CALCULATE_DISTANCE) {
            throw new ProcessException("前面的数据还没有准备好");
        }
    }

    protected void generalAfterExecute(int questionId, JavaMailSender mailSender,
                                       String userEmail, String algorithm) {
        sendMail(userEmail, questionId, algorithm, mailSender);
    }

    private void sendMail(String toMail, int questionId,
                          String algorithm, JavaMailSender mailSender) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("campus.mis@foxmail.com");
        simpleMailMessage.setTo(toMail);
        simpleMailMessage.setSubject("路径规划系统通知");
        String text = "您问题编号为 " + questionId + " 执行" + algorithm +"已经完成";
        simpleMailMessage.setText(text);
        mailSender.send(simpleMailMessage);
    }


}
