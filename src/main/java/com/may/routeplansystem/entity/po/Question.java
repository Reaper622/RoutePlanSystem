package com.may.routeplansystem.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 10587
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    private int questionId;
    private String questionName;
    private int userId;
    private byte delFlag;
}
