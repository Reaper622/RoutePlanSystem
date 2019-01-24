package com.may.routeplansystem.algorithm;

import com.may.routeplansystem.exception.ParameterException;

import java.util.HashMap;
import java.util.Map;

public class AlgorithmContext {

    private Map<Integer, AlgorithmExecutor> map = new HashMap<>();

    public void addAlgorithm(int key, AlgorithmExecutor algorithmExecutor) {
        map.put(key, algorithmExecutor);
    }

    public void execute(int key, int questionId) {
        AlgorithmExecutor algorithmExecutor = map.get(key);
        if (algorithmExecutor == null) {
            throw new ParameterException("没有指定的算法");
        }
        algorithmExecutor.execute(questionId);
    }

    public Map<Integer, AlgorithmExecutor> getMap() {
        return map;
    }
}
