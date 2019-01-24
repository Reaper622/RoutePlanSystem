package com.may.routeplansystem.controller;

import com.may.routeplansystem.algorithm.AlgorithmContext;
import com.may.routeplansystem.constant.Response;
import com.may.routeplansystem.entity.dto.ResponseEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

import static com.may.routeplansystem.constant.ResponseStatu.SUCCESS;

/**
 * @author 10587
 */
@RestController("algorithm")
@Api(tags = "算法")
@Validated
public class AlgorithmController {

    @Resource
    private AlgorithmContext algorithmContext;

    @GetMapping("executeAlgorithm")
    @ApiOperation("选择算法并执行")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key", paramType = "query", value = "1 表示执行简单算法; 2 表示执行遗传算法", required = true),
            @ApiImplicitParam(name = "questionId",paramType = "query", value = "问题Id", required = true)
    })
    public ResponseEntity executeAlgorithm(@NotNull(message = "算法key值不能为空") Integer key,
                                           @NotNull(message = "问题Id不能为空") Integer questionId) {
        algorithmContext.execute(key, questionId);
        return new ResponseEntity<>(SUCCESS, Response.SUCCESSFUL, null);
    }
}
