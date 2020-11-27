package com.cloud.item.controller;

import com.cloud.item.model.Departure;
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

import static com.cloud.item.constant.Paths.DEPARTURE;
import static com.cloud.item.constant.Paths.VERSION;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(DepartureController.class)
class DepartureControllerTest {

    @MockBean
    private DepartureController departureController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllDepartures() throws Exception {
        Departure departure = new Departure();
        departure.setCity("Meerut");

        List<Departure> departureList = Collections.singletonList(departure);

        when(departureController.getAllDepartures()).thenReturn(departureList);

        mockMvc.perform(get(VERSION + DEPARTURE + "all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].city", is(departure.getCity())));
    }

    @Test
    void getDepartureById() throws Exception {
        Departure departure = new Departure();
        departure.setCity("Meerut");
        when(departureController.getDepartureById(departure.getId())).thenReturn(departure);

        mockMvc.perform(get(VERSION + DEPARTURE + departure.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("city", is(departure.getCity())));
    }
}