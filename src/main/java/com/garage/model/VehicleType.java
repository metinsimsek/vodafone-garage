package com.garage.model;

public enum VehicleType {

    CAR(1), JEEP(2), TRUCK(4);

    private final int value;

    private VehicleType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
