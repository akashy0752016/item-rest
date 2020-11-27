package com.cloud.item.controller;

import com.cloud.item.model.Arrival;
import com.cloud.item.repository.ArrivalRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import java.util.Collections;
import java.util.List;

import static com.cloud.item.constant.Paths.ARRIVAL;
import static com.cloud.item.constant.Paths.VERSION;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ArrivalController.class)
class ArrivalControllerTest {

    @MockBean
    private ArrivalController arrivalController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllArrivals() throws Exception {
        Arrival arrival = new Arrival();
        arrival.setCity("Yerevan");

        List<Arrival> arrivalList = Collections.singletonList(arrival);

        when(arrivalController.getAllArrivals()).thenReturn(arrivalList);

        mockMvc.perform(get(VERSION + ARRIVAL + "all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].city", is(arrival.getCity())));
    }

    @Test
    void getArrivalById() throws Exception {
        Arrival arrival = new Arrival();
        arrival.setCity("Yerevan");

        when(arrivalController.getArrivalById(arrival.getId())).thenReturn(arrival);

        mockMvc.perform(get(VERSION + ARRIVAL + arrival.getId())
            .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("city", is(arrival.getCity())));
    }
}