package com.garage.model;

import java.util.List;

public class Park {

    private String plate;
    private String color;
    private VehicleType vehicleType;
    private List<Integer> parkingSlot;



    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public List<Integer> getParkingSlot() {
        return parkingSlot;
    }

    public void setParkingSlot(List<Integer> parkingSlot) {
        this.parkingSlot = parkingSlot;
    }

    @Override
    public String toString() {
        return "Park{" +
                "plate='" + plate + '\'' +
                ", color='" + color + '\'' +
                ", vehicleType=" + vehicleType +
                ", parkingSlot=" + parkingSlot +
                '}';
    }

}
