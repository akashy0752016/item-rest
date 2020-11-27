package com.cloud.item.controller;

import com.cloud.item.model.Arrival;
import com.cloud.item.repository.ArrivalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.cloud.item.constant.Paths.ARRIVAL;
import static com.cloud.item.constant.Paths.VERSION;

@RequestMapping(value = VERSION + ARRIVAL)
@RestController
public class ArrivalController {

    @Autowired
    private ArrivalRepository arrivalRepository;

    @RequestMapping(value = "all", method = RequestMethod.GET)
    @ResponseBody
    public List<Arrival> getAllArrivals() {
        return arrivalRepository.findAll();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ResponseBody
    public Arrival getArrivalById(@PathVariable(value = "id") int id) {
        return arrivalRepository.findAllById(id);
    }

}
