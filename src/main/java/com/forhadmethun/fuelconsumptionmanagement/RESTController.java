package com.forhadmethun.fuelconsumptionmanagement;

import com.forhadmethun.fuelconsumptionmanagement.registration.*;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "")
public class RESTController {
    @Autowired
    private RegistrationService registrationService;

    @RequestMapping(value = "/get-all-fuel-registration-data", method = RequestMethod.GET)
    public List<RegistrationDTO> getAllFuelRegistrationData() {
        return registrationService.getAllFuelRegistrationData();
    }

    @RequestMapping(value = "/create-fuel-registration", method = RequestMethod.POST)
    public Map<String, String> createNewRegistration(@RequestBody List<RegistrationDTO> registrationDTOS) {
        return registrationService.createNewRegistration(registrationDTOS);
    }

    @RequestMapping(value = "/get-fuel-consumption-record", method = RequestMethod.POST)
    public Object getFuelRegistrationDataByMonth(@RequestBody JSONObject data) {
        return registrationService.getFuelRegistrationDataByMonth(data);
    }

    @RequestMapping(value = "/get-fuel-consumption-statistics", method = RequestMethod.POST)
    public Object getFuelRegistrationStatistics(@RequestBody JSONObject data) {
        return registrationService.getFuelRegistrationStatistics(data);
    }

    @RequestMapping(value = "/get-total-spent-amount", method = RequestMethod.POST)
    public Object getAllR(@RequestBody JSONObject data) {
        return registrationService.getAllR(data);
    }

}
