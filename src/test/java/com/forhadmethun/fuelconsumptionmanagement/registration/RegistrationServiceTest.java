package com.forhadmethun.fuelconsumptionmanagement.registration;

import org.json.simple.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RegistrationServiceTest {

    @TestConfiguration
    static class RegistrationServiceTestContextConfiguration
    {
        @Bean
        public RegistrationService registrationServiceTest(){
            return new RegistrationService();
        }
    }

    @Autowired private RegistrationRepository registrationRepository;
    @Autowired private RegistrationService registrationService;

    @Test public void createFuelRegistrationTest() {
        List<RegistrationDTO> registrationDTOS = new ArrayList<>();
        registrationDTOS.add(new RegistrationDTO(1L, "D",10.0,4.0,"12345","01.21.2018"));
        registrationDTOS.add(new RegistrationDTO(2L, "D",10.0,4.0,"12345","01.21.2018"));
        assertThat(registrationService.createFuelRegistration(registrationDTOS).get("message")).isEqualTo("Data inserted successfully."); //isEqualTo("01");

        RegistrationEntity registrationEntity = this.registrationRepository.findByIdEquals(1L);
        assertThat(registrationEntity.getMonth()).isEqualTo(1); //isEqualTo("01");

    }

    @Test public void getAllFuelRegistrationDataTest() {
        /*inserting few data to db*/
        List<RegistrationDTO> registrationDTOS = new ArrayList<>();
        registrationDTOS.add(new RegistrationDTO(1L, "D",10.0,4.0,"12345","01.21.2018"));
        registrationDTOS.add(new RegistrationDTO(2L, "D",10.0,4.0,"12345","01.21.2018"));

        /*checking if data inserted successfully*/
        assertThat(registrationService.createFuelRegistration(registrationDTOS).get("message")).isEqualTo("Data inserted successfully."); //isEqualTo("01");

        /*checking if all data can be retrieved successfully*/
        int dataSize = this.registrationRepository.findAll().size();
        assertThat(dataSize).isEqualTo(2);
    }

    @Test public void getFuelConsumptionRecordTest() {
        /*inserting few data to db*/
        List<RegistrationDTO> registrationDTOS = new ArrayList<>();
        registrationDTOS.add(new RegistrationDTO(1L, "D",10.0,4.0,"12345","01.21.2018"));
        registrationDTOS.add(new RegistrationDTO(2L, "D",10.0,4.0,"12345","01.21.2018"));

        /*checking if data inserted successfully*/
        assertThat(registrationService.createFuelRegistration(registrationDTOS).get("message")).isEqualTo("Data inserted successfully."); //isEqualTo("01");

        /*checking if all data can be retrieved successfully*/
        int dataSize = this.registrationRepository.findAll().size();
        assertThat(dataSize).isEqualTo(2);

        JSONObject data = new JSONObject();
        data.put("year", 2018);

        List<Object> record =(List<Object>) registrationService.getFuelConsumptionRecord(data);
        HashMap<String, Object> obj = (HashMap<String, Object>) record.get(0);

        List<HashMap<String, List<Object>>> recordArr =  (List<HashMap<String, List<Object>>>) obj.get("records");
        HashMap<String, List<Object>> recordMap =  recordArr.get(0);

        /* checking if two records exists or not. */
        assertThat(recordMap.get("data").size()).isEqualTo(2);
        /* check year record exists or not.*/
        assertThat((int)obj.get("year")).isEqualTo(2018);

    }

    //todo change the method body
    @Test public void getFuelConsumptionStatisticsTest() {
        /*inserting few data to db*/
        List<RegistrationDTO> registrationDTOS = new ArrayList<>();
        registrationDTOS.add(new RegistrationDTO(1L, "D",10.0,4.0,"12345","01.21.2018"));
        registrationDTOS.add(new RegistrationDTO(2L, "D",10.0,4.0,"12345","01.21.2018"));

        /*checking if data inserted successfully*/
        assertThat(registrationService.createFuelRegistration(registrationDTOS).get("message")).isEqualTo("Data inserted successfully."); //isEqualTo("01");

        /*checking if all data can be retrieved successfully*/
        int dataSize = this.registrationRepository.findAll().size();
        assertThat(dataSize).isEqualTo(2);

        JSONObject data = new JSONObject();
        data.put("year", 2018);

        List<Object> record =(List<Object>) registrationService.getFuelConsumptionRecord(data);
        HashMap<String, Object> obj = (HashMap<String, Object>) record.get(0);

        List<HashMap<String, List<Object>>> recordArr =  (List<HashMap<String, List<Object>>>) obj.get("records");
        HashMap<String, List<Object>> recordMap =  recordArr.get(0);

        /* checking if two records exists or not. */
        assertThat(recordMap.get("data").size()).isEqualTo(2);
        /* check year record exists or not.*/
        assertThat((int)obj.get("year")).isEqualTo(2018);

    }

    //todo change the method body
    @Test public void getTotalSpentAmountTest() {
        /*inserting few data to db*/
        List<RegistrationDTO> registrationDTOS = new ArrayList<>();
        registrationDTOS.add(new RegistrationDTO(1L, "D",10.0,4.0,"12345","01.21.2018"));
        registrationDTOS.add(new RegistrationDTO(2L, "D",10.0,4.0,"12345","01.21.2018"));

        /*checking if data inserted successfully*/
        assertThat(registrationService.createFuelRegistration(registrationDTOS).get("message")).isEqualTo("Data inserted successfully."); //isEqualTo("01");

        /*checking if all data can be retrieved successfully*/
        int dataSize = this.registrationRepository.findAll().size();
        assertThat(dataSize).isEqualTo(2);

        JSONObject data = new JSONObject();
        data.put("year", 2018);

        List<Object> record =(List<Object>) registrationService.getFuelConsumptionRecord(data);
        HashMap<String, Object> obj = (HashMap<String, Object>) record.get(0);

        List<HashMap<String, List<Object>>> recordArr =  (List<HashMap<String, List<Object>>>) obj.get("records");
        HashMap<String, List<Object>> recordMap =  recordArr.get(0);

        /* checking if two records exists or not. */
        assertThat(recordMap.get("data").size()).isEqualTo(2);
        /* check year record exists or not.*/
        assertThat((int)obj.get("year")).isEqualTo(2018);

    }

}
