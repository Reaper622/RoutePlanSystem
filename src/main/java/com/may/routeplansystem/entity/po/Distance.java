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
public class Distance {
    private int distanceId;
    private int questionId;
    private int startNodeId;
    private int endNodeId;
    private String startNodeAddr;
    private String endNodeAddr;
    private int dis;
    private int time;
}
