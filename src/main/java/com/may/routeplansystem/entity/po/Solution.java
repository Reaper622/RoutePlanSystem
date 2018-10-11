package com.may.routeplansystem.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author May
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Solution {
    private int solutionId;
    private int questionId;
    private String route;
    private double totalDis;
    private double totalTime;
    private int version;
    private byte delFlag;
}
