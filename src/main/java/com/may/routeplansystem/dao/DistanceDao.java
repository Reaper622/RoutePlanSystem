package com.may.routeplansystem.dao;

import com.may.routeplansystem.entity.po.Distance;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author May
 */
@Repository
public interface DistanceDao {

    boolean insertDis(Distance distance);

    boolean insertDistances(List<Distance> list);

    boolean removeDis(int distanceId);

    boolean updateDisAndTime(Distance distance);

    Distance findUpdateDistances(int questionId);

}
