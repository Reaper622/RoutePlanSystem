package com.may.routeplansystem.entity.vo;

import lombok.Data;

import java.util.List;

@Data
public class FinalSolutionVo {

    private int finalSolutionId;
    private List<String> routes;
    private double totalDis;
    private int userChoice;
    private String createTime;
}
