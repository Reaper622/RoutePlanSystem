package com.may.routeplansystem.controller;

import com.may.routeplansystem.constant.Response;
import com.may.routeplansystem.entity.dto.ResponseEntity;
import com.may.routeplansystem.entity.po.Question;
import com.may.routeplansystem.service.QuestionService;
import com.may.routeplansystem.util.validation.insertAndUpdateGroup.Insert;
import com.may.routeplansystem.util.validation.insertAndUpdateGroup.Update;
import io.swagger.annotations.*;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

import static com.may.routeplansystem.constant.ResponseStatu.SUCCESS;

/**
 * @author 10587
 */
@RestController
@RequestMapping("question")
@Api(tags = "问题模块")
public class QuestionController {

    @Resource
    private QuestionService questionService;

    @PostMapping("insertQuestion")
    @ApiOperation("添加问题")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "questionName",value = "问题名称", required = true),
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true)
    })
    public ResponseEntity insertQuestion(@Validated(Insert.class) Question question, BindingResult result) {
        if (result.hasErrors()){
            String message = result.getAllErrors().get(0).getDefaultMessage();
            return new ResponseEntity<>(SUCCESS, Response.SUCCESSFUL, message);
        }
        int questionId = questionService.insertQuestion(question);
        return new ResponseEntity<>(SUCCESS, Response.SUCCESSFUL, questionId);
    }

    @Validated
    @GetMapping("getQuestions")
    @ApiOperation("获取该用户所有问题信息")
    @ApiImplicitParam(name = "userId", value = "用户Id", paramType = "query")
    public ResponseEntity<List<Question>> getQuestions
            (@NotNull(message = "userId不能为空") @RequestParam(value = "userId") Integer userId) {
        List<Question> questions = questionService.getQuestions(userId);
        return new ResponseEntity<>(SUCCESS, Response.SUCCESSFUL, questions);
    }

    @Validated
    @DeleteMapping("removeQuestion")
    @ApiOperation("删除问题")
    public ResponseEntity removeQuestion(@NotNull(message = "questionId不能为空") int questionId) {
        questionService.removeQuestion(questionId);
        return new ResponseEntity<>(SUCCESS, Response.SUCCESSFUL, null);
    }

    @PatchMapping("updateQuestion")
    @ApiOperation("修改问题信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "questionId", dataType = "int", value = "问题Id"),
            @ApiImplicitParam(name = "questionName", dataType = "string", value = "问题名称")
    })
    @ApiImplicitParam(name = "questionName", dataType = "string", value = "问题名称")
    public ResponseEntity updateQuestion(@Validated(Update.class) Question question, BindingResult result) {
        if (result.hasErrors()){
            String message = result.getAllErrors().get(0).getDefaultMessage();
            return new ResponseEntity<>(SUCCESS, Response.SUCCESSFUL, message);
        }
        questionService.updateQuestion(question);
        return new ResponseEntity<>(SUCCESS, Response.SUCCESSFUL, null);
    }
}
