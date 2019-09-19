package com.forhadmethun.fuelconsumptionmanagement.registration;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegistrationDTO {
    private long id;
    private String fuelType;
    private Double pricePerLiter;
    private Double volume;
    private String driverId;
    private String date;
}
