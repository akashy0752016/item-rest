package com.cloud.item.controller;

import com.cloud.item.model.Flight;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static com.cloud.item.constant.Paths.FLIGHT;
import static com.cloud.item.constant.Paths.VERSION;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(FlightController.class)
class FlightControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FlightController flightController;

    @Test
    void getAllFlights() throws Exception {
        Flight flight = createFlight();
        List<Flight> flightList = Collections.singletonList(flight);

        when(flightController.getAllFlights()).thenReturn(flightList);

        mockMvc.perform(get(VERSION + FLIGHT + "all").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].airline", is(flight.getAirline())));
    }

    @Test
    void getFlightById() throws Exception {
        Flight flight = createFlight();

        when(flightController.getFlightById(flight.getId())).thenReturn(flight);

        mockMvc.perform(get(VERSION + FLIGHT + flight.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("airline", is(flight.getAirline())));
    }

    private Flight createFlight() {
        Flight flight = new Flight();
        flight.setAirline("Virgin");
        flight.setDepartureTime("20:00:00");
        flight.setArrivalTime("23:20:00");
        flight.setNumber(432);
        flight.setPrice("$3000");

        return flight;
    }
}