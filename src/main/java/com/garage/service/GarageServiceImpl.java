package com.garage.service;

import com.garage.exception.ParkingException;
import com.garage.model.Events;
import com.garage.model.Park;
import com.garage.model.Slots;
import com.garage.util.Constant;
import com.garage.util.GarageUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class GarageServiceImpl implements IGarageService {

    @Override
    public List<Park> statusPark() {

        List<Park> result = new ArrayList<>();
        Slots.parkingMap.entrySet().stream().forEach(e -> result.add(e.getValue()));

        return result;
    }

    @Override
    public Events parkVehicle(Park parking) {

        int[] slots = GarageUtil.getAvailableSlots(parking);
        Park vehicle = new Park();
        vehicle.setColor(parking.getColor());
        vehicle.setPlate(parking.getPlate());
        vehicle.setVehicleType(parking.getVehicleType());
        vehicle.setParkingSlot(Arrays.stream(slots).boxed().collect(Collectors.toList()));

        GarageUtil.updateAvailableSlots(vehicle);
        return new Events("Allocated " + Arrays.toString(slots) + " slot.");

    }

    @Override
    public Events leavePark(int parkingId) {

        List<Integer> parkingSlots = Slots.parkingMap.get(parkingId).getParkingSlot();
        if (parkingSlots == null) {
            throw new ParkingException(Constant.INVALID_PARKING_SlOT);
        }
        log.info("parkingSlots " + parkingSlots);
        GarageUtil.leaveParking(parkingId, parkingSlots);
        return new Events("Leave slot: " + parkingId);

    }
}
