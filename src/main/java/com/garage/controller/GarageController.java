package com.garage.controller;

import com.garage.exception.ParkingException;
import com.garage.model.Events;
import com.garage.model.Park;
import com.garage.service.IGarageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/garage")
public class GarageController {

    @Autowired
    private IGarageService garageService;

    @GetMapping(path = "/status", produces = "application/json")
    public ResponseEntity<List<Park>> statusPark() {
        List<Park> result = garageService.statusPark();
        return ResponseEntity.ok(result);
    }

    @PostMapping(path = "/include", produces = "application/json")
    public ResponseEntity<Events> includePark(@RequestBody Park parking) {
        Events result = null;
        try {
            result = garageService.parkVehicle(parking);
        } catch (ParkingException e) {
            return ResponseEntity.ok(new Events(e.getMessage()));
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping(path = "/leave/{parkingId}", produces = "application/json")
    public ResponseEntity<Events> leavePark(@PathVariable("parkingId") int parkingId) {
        Events result = null;
        try {
            result = garageService.leavePark(parkingId);
        } catch (ParkingException e) {
            return ResponseEntity.ok(new Events(e.getMessage()));
        }
        return ResponseEntity.ok(result);
    }
}
