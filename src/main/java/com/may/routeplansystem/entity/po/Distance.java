package com.may.routeplansystem.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author 10587
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Distance {
    private int distanceId;
    private int questionId;
    private int startNodeId;
    private int endNodeId;
    private int dis;
    private int time;
}
