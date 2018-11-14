package com.may.routeplansystem.util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MMath {

    public static int randomIntExceptZero(int routeRandom) {
        if (routeRandom == 1){
            return 1;
        }
        int random = (int) (Math.random() * routeRandom);
        while (random == 0) {
            random = (int) (Math.random() * routeRandom);
        }
        return random;
    }


    public static int randomInt(int start, int end){
        return (int) (Math.random() * end) + start;
    }

}
