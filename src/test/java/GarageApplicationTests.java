
import com.fasterxml.jackson.databind.ObjectMapper;
import com.garage.model.Events;
import com.garage.model.Park;
import com.garage.model.VehicleType;
import com.garage.service.IGarageService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@AutoConfigureMockMvc
public class GarageApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private  IGarageService garageService;

    private static ObjectMapper mapper = new ObjectMapper();


    @Test
    void parkVehicleWithCar() throws Exception {
        parkVehicle(VehicleType.CAR);
    }

    @Test
    void parkVehicleWithJEEP() throws Exception {
        parkVehicle(VehicleType.JEEP);
    }

    @Test
    void parkVehicleWithTRUCK() throws Exception {
        parkVehicle(VehicleType.TRUCK);
    }

    public void parkVehicle(VehicleType vehicleType) throws Exception {

        Park parking = new Park();
        parking.setColor("blue");
        parking.setPlate("34JE006");
        parking.setVehicleType(vehicleType);
        List<Integer> parkingSlot = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        parking.setParkingSlot(parkingSlot);

        Events events = new Events("Allocated " + vehicleType.getValue() + " slot");
        Mockito.when(this.garageService.parkVehicle(parking)).thenReturn(events);

        String json = mapper.writeValueAsString(parking);


        MvcResult result = (MvcResult) mockMvc.perform(MockMvcRequestBuilders.post("api/garage/include")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        String resultDOW = result.getResponse().getContentAsString();
        assertNotNull(resultDOW);
        assertEquals(vehicleType.getValue(), resultDOW);

    }

    @Test
    void testStatusPark() {

        List<Park> parkingList = new ArrayList<>();
        Park parking = new Park();
        parking.setColor("black");
        parking.setPlate("34JE007");
        parking.setVehicleType(VehicleType.JEEP);

        List<Integer> parkingSlot = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        parking.setParkingSlot(parkingSlot);

        parkingList.add(parking);

        Mockito.when(garageService.statusPark()).thenReturn(parkingList);
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("api/garage/status")).andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Test
    void LeavePark() throws Exception {
        //parkVehicle(VehicleType.JEEP);
        Events events = new Events("Leave parkingId: 1");
        Mockito.when(this.garageService.leavePark(1)).thenReturn(events);

        String json = mapper.writeValueAsString(events);

        MvcResult result = (MvcResult) mockMvc.perform(MockMvcRequestBuilders.delete("api/garage/leave/1")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        String resultDOW = result.getResponse().getContentAsString();
        assertNotNull(resultDOW);
        assertEquals(events, resultDOW);

    }
}
