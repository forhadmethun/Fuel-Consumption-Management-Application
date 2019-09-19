package com.forhadmethun.fuelconsumptionmanagement;

import com.forhadmethun.fuelconsumptionmanagement.registration.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "")
@Api(value="Fuel Consumption Management System", description="Operations related to to fuel consumption in Fuel Consumption Management System")
public class RESTController {
    @Autowired
    private RegistrationService registrationService;
    @RequestMapping(value = "/create-fuel-registration", method = RequestMethod.POST)
    public Map<String, String> createFuelRegistration(@RequestBody List<RegistrationDTO> registrationDTOS) {
        return registrationService.createFuelRegistration(registrationDTOS);
    }
    @RequestMapping(value = "/get-total-spent-amount", method = RequestMethod.POST)
    public Object getTotalSpentAmount(
            @ApiParam(value=RegistrationUtility.GET_TOTAL_SPENT_AMOUNT_API_REQUEST, required = true)
            @RequestBody JSONObject data) {
        return registrationService.getTotalSpentAmount(data);
    }
    @RequestMapping(value = "/get-fuel-consumption-record", method = RequestMethod.POST)
    public Object getFuelConsumptionRecord(
            @ApiParam(value = RegistrationUtility.GET_SPECIFIED_MONTH_FUEL_CONSUMPTION_API_REQUEST, required = true)
            @RequestBody JSONObject data) {
        return registrationService.getFuelConsumptionRecord(data);
    }
    @RequestMapping(value = "/get-fuel-consumption-statistics", method = RequestMethod.POST)
    public Object getFuelConsumptionStatistics(
            @ApiParam(value = RegistrationUtility.GET_FUEL_CONSUMPTION_STAT_API_REQUEST, required = true)
            @RequestBody JSONObject data) {
        return registrationService.getFuelConsumptionStatistics(data);
    }

    @RequestMapping(value = "/get-all-fuel-registration-data", method = RequestMethod.GET)
    public List<RegistrationDTO> getAllFuelRegistrationData() {
        return registrationService.getAllFuelRegistrationData();
    }
}
