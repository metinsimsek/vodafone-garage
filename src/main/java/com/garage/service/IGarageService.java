package com.garage.service;

import com.garage.model.Events;
import com.garage.model.Park;
import java.util.List;


public interface IGarageService {

    public List<Park> statusPark();

    public Events parkVehicle(Park parking);

    public Events leavePark(int parkingId);

}
