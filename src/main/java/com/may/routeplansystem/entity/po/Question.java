package com.may.routeplansystem.entity.po;

import com.may.routeplansystem.util.validation.insertAndUpdateGroup.Insert;
import com.may.routeplansystem.util.validation.insertAndUpdateGroup.Update;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author 10587
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class Question {

    @NotNull(message = "questionId 不能为空", groups = Insert.class)
    @ApiModelProperty("问题Id") private int questionId;
    @NotNull(message = "questionName 不能为空", groups = {Insert.class, Update.class})
    @Size(max = 20, message = "questionName 不能超过20个字符", groups = {Insert.class, Update.class})
    @ApiModelProperty("问题名称") private String questionName;
    @NotNull(message = "userId 不能为空", groups = Insert.class)
    @ApiModelProperty("问题所属的用户Id") private int userId;
    @ApiModelProperty("执行状态")private int processState;
    private int simpleExecuted;
    private int geneticExecuted;
    private int delFlag;
}
