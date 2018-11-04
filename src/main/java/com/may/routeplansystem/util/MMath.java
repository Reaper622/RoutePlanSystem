package com.may.routeplansystem.util;

public class MMath {

    public static int randomIntExceptZero(int start, int end){
        int random = 0;
        while (random == 0){
            random = (int) (Math.random() * end) + start;
        }
        return random;
    }

    public static int randomInt(int start, int end){
        return (int) (Math.random() * end) + start;
    }

}
