package com.garage.util;

import com.garage.exception.ParkingException;
import com.garage.model.Park;
import com.garage.model.Slots;
import com.garage.model.VehicleType;
import java.util.stream.Collectors;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class GarageUtil {

    public static int[] getAvailableSlots(Park parking) {

        int[] result = null;
        if(parking.getVehicleType() ==VehicleType.CAR) {
            if (Slots.availableSlots.length >= VehicleType.CAR.getValue()) {
                result = new int[] { Slots.availableSlots[0] };
                log.info("result CAR " + result);
            } else {
                throw new ParkingException(Constant.GARAGE_FULL);
            }
        } else if(parking.getVehicleType() ==VehicleType.JEEP) {
            if (Slots.availableSlots.length >= VehicleType.JEEP.getValue()) {
                result = new int[] { Slots.availableSlots[0], Slots.availableSlots[1] };
                log.info("result JEEP " + result);

            } else {
                throw new ParkingException(Constant.GARAGE_FULL);
            }
        } else if(parking.getVehicleType() ==VehicleType.TRUCK) {
            if (Slots.availableSlots.length >= VehicleType.TRUCK.getValue()) {
                result = new int[] { Slots.availableSlots[0], Slots.availableSlots[1],
                                    Slots.availableSlots[2], Slots.availableSlots[3]};
                log.info("result TRUCK " + result);

            } else {
                throw new ParkingException(Constant.GARAGE_FULL);
            }
        }

        return result;
    }

    public static void updateAvailableSlots(Park parking) {

        List<Integer> slotList = parking.getParkingSlot();
        List<Integer> availableSlotList = Arrays.stream(Slots.availableSlots).boxed()
                .collect(Collectors.toList());
        availableSlotList.removeAll(slotList);
        Slots.availableSlots = availableSlotList.stream().mapToInt(i -> i).toArray();
        updateParking(parking);

    }

    public static void updateParking(Park parking) {
        if (Slots.parkingMap.size() > 0) {
            Slots.parkingMap.put(Slots.parkingMap.size() + 1, parking);
        } else { //for the first time parking
            Slots.parkingMap.put(1, parking);
        }
    }

    public static void leaveParking(int parkingId, List<Integer> parkingSlots) {

        List<Integer> availableSlots = Arrays.stream(Slots.availableSlots).boxed()
                .collect(Collectors.toList());
        List<Integer> newList = Stream.concat(parkingSlots.stream(), availableSlots.stream())
                .collect(Collectors.toList());
        Slots.availableSlots = newList.stream().mapToInt(i -> i).toArray();
        Slots.parkingMap.remove(parkingId);

    }




}
