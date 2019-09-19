package com.forhadmethun.fuelconsumptionmanagement.registration;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "fuel_consumption_registration")
public class RegistrationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String fuelType;
    private Double pricePerLiter;
    private Double volume;
    private String driverId;

    private Integer month;
    private Integer day;
    private Integer  year;
}
