package com.cloud.item.controller;

import com.cloud.item.model.Departure;
import com.cloud.item.repository.DepartureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.cloud.item.constant.Paths.DEPARTURE;
import static com.cloud.item.constant.Paths.VERSION;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(value = VERSION + DEPARTURE)
public class DepartureController {

    @Autowired
    private DepartureRepository departureRepository;

    @RequestMapping(value = "all", method = GET)
    @ResponseBody
    public List<Departure> getAllDepartures() {
        return departureRepository.findAll();
    }

    @RequestMapping(value = "{id}", method = GET)
    @ResponseBody
    public Departure getDepartureById(@PathVariable(value = "id") int id) {
        return departureRepository.findAllById(id);
    }
}
