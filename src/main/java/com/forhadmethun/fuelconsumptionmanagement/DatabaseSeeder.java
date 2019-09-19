package com.forhadmethun.fuelconsumptionmanagement;

import com.forhadmethun.fuelconsumptionmanagement.registration.RegistrationEntity;
import com.forhadmethun.fuelconsumptionmanagement.registration.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DatabaseSeeder implements CommandLineRunner {
    private RegistrationRepository registrationRepository;
    @Autowired
    public DatabaseSeeder(RegistrationRepository registrationRepository){
        this.registrationRepository =registrationRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        List<RegistrationEntity> registrationEntities = new ArrayList<>();
        registrationEntities.add(new RegistrationEntity(1L,"D", 10.10, 12.5, "11111",01,02,2019));
        registrationEntities.add(new RegistrationEntity(2L,"D", 10.10, 12.5, "11111",01,03,2016));
        registrationEntities.add(new RegistrationEntity(0L,"E", 12.10, 12.5, "11111",01,03,2016));
        registrationEntities.add(new RegistrationEntity(3L,"D", 10.10, 12.5, "12345",01,03,2016));
        registrationEntities.add(new RegistrationEntity(4L,"D", 10.10, 12.5, "12345",01,04,2017));
        registrationEntities.add(new RegistrationEntity(0L,"D", 10.10, 12.5, "12345",06,04,2017));
        registrationEntities.add(new RegistrationEntity(0L,"D", 10.10, 12.5, "12346",07,1,2018));
        registrationEntities.add(new RegistrationEntity(0L,"D", 10.10, 12.5, "12346",07,15,2018));
        registrationRepository.save(registrationEntities);

    }
}
