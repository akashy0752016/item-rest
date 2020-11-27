package com.cloud.item.controller;

import com.cloud.item.model.OriginalData;
import com.cloud.item.repository.OriginalDataRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.cloud.item.constant.Paths.OD;
import static com.cloud.item.constant.Paths.VERSION;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = VERSION + OD)
public class OriginalDataController {

    @Autowired
    private OriginalDataRepository odRepository;

    @RequestMapping(method = POST)
    @ResponseBody
    public void setUserData(@RequestBody Map<String, String> originalData) throws IOException {
        ObjectMapper om = new ObjectMapper();
        for (int i = 0; i < 1500; i++) {
            OriginalData od = new OriginalData();
            od.setData(om.writeValueAsString(originalData));
            odRepository.save(od);
        }
    }
}
