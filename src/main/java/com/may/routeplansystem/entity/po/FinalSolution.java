package com.may.routeplansystem.entity.po;

import lombok.Data;

@Data
public class FinalSolution {
    private int finalSolutionId;
    private int questionId;
    private double totalDis;
    private int version;
    private int userChoice;
}
