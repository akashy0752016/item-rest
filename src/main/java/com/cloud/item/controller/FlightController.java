package com.cloud.item.controller;

import com.cloud.item.model.Flight;
import com.cloud.item.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.cloud.item.constant.Paths.FLIGHT;
import static com.cloud.item.constant.Paths.VERSION;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(value = VERSION + FLIGHT)
public class FlightController {

    @Autowired
    private FlightRepository flightRepository;

    @RequestMapping(value = "all", method = GET)
    @ResponseBody
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    @RequestMapping(value = "{id}", method = GET)
    @ResponseBody
    public Flight getFlightById(@PathVariable(value = "id") int id) {
        return flightRepository.findAllById(id);
    }
}
