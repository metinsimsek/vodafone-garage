package com.garage.model;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Slots {

    public static int[] availableSlots = new int[] {1,2,3,4,5,6,7,8,9,10};
    public static Map<Integer, Park> parkingMap = new ConcurrentHashMap<>();


}
