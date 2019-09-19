package com.forhadmethun.fuelconsumptionmanagement.registration;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RegistrationService {
    @Autowired
    private RegistrationRepository registrationRepository;

    public List<RegistrationDTO> getAllFuelRegistrationData() {
        return RegistrationUtility.getRegistrationDTOListFromRegistrationEntityList(registrationRepository.findAll());
    }

    public Map<String, String> createNewRegistration(List<RegistrationDTO> registrationDTOS) {
        Map<String, String> message = new HashMap<String, String>();
        if (!RegistrationUtility.validate(registrationDTOS, message)) {
            return message;
        }
        List<RegistrationEntity> registrationEntities = new ArrayList<>();
        for (RegistrationDTO registrationDTO : registrationDTOS) {
            registrationEntities.add(RegistrationUtility.getRegistrationEntityFromRegistrationDTO(registrationDTO));
        }
        registrationRepository.save(registrationEntities);
        message.put("message", "Data inserted successfully.");
        return message;
    }


    public Object getFuelRegistrationDataByMonth(JSONObject data) {

        Integer givenYear = (Integer) data.get("year");
        Integer givenMonth = (Integer) data.get("month");

        List<RegistrationEntity> registrationEntityList = null;
        if (givenMonth == null && givenYear == null) {
            registrationEntityList = registrationRepository.findAll();
        } else if (givenMonth != null && givenYear != null) {
            registrationEntityList = registrationRepository.findByYearEqualsAndMonthEquals(givenYear, givenMonth);
        } else if (givenMonth != null && givenYear == null) {
            Map<String, String> message = new HashMap<>();
            message.put("error", "Bad Request");
            message.put("message", "month parameter without year parameter is unacceptable.");
            return message;
        } else {
            registrationEntityList = registrationRepository.findByYearEquals(givenYear);
        }
        Map<Integer, List<RegistrationEntity>> groupByYear = registrationEntityList.stream().collect(Collectors.groupingBy(w -> w.getYear()));

        List<Object> responseListObject = new ArrayList<>();

        Map<Integer, Object> responseData = new HashMap<Integer, Object>();

        for (Integer year : groupByYear.keySet()) {
            Map<String, Object> yearData = new HashMap<>();
            List<Object> monthlyCostList = new ArrayList<>();
            Map<Integer, List<RegistrationEntity>> groupByMonthData = groupByYear.get(year).stream().collect(Collectors.groupingBy(m -> m.getMonth()));
            Map<String, Object> modifiedGroupByMonthData = new HashMap<>();
            List<Object> monthlyDataList = new ArrayList<>();
            for (Integer month : groupByMonthData.keySet()) {
                {
                    List<RegistrationEntity> registrationEntities = groupByMonthData.get(month);
                    modifiedGroupByMonthData.put("month", RegistrationUtility.getMonth(month));
                    modifiedGroupByMonthData.put("data", RegistrationUtility.getRegistrationDTOListFromRegistrationEntityList(registrationEntities));
                    monthlyDataList.add(modifiedGroupByMonthData);
                }
            }

            yearData.put("year", year);
            yearData.put("records", monthlyDataList);

            responseListObject.add(yearData);
        }
        return responseListObject;
    }


    public Object getFuelRegistrationStatistics(JSONObject data) {

        Integer givenYear = (Integer) data.get("year");
        Integer givenMonth = (Integer) data.get("month");

        List<RegistrationEntity> registrationEntityList = null;
        if (givenMonth == null && givenYear == null) {
            registrationEntityList = registrationRepository.findAll();
        } else if (givenMonth != null && givenYear != null) {
            registrationEntityList = registrationRepository.findByYearEqualsAndMonthEquals(givenYear, givenMonth);
        } else if (givenMonth != null && givenYear == null) {
            Map<String, String> message = new HashMap<>();
            message.put("error", "Bad Request");
            message.put("message", "month parameter without year parameter is unacceptable.");
            return message;
        } else {
            registrationEntityList = registrationRepository.findByYearEquals(givenYear);
        }
        Map<Integer, List<RegistrationEntity>> groupByYear = registrationEntityList.stream().collect(Collectors.groupingBy(w -> w.getYear()));
        List<Object> responseListObject = new ArrayList<>();
        for (Integer year : groupByYear.keySet()) {
            Map<String, Object> yearData = new HashMap<>();
            List<Object> monthlyCostList = new ArrayList<>();
            Map<Integer, List<RegistrationEntity>> groupByMonthData = groupByYear.get(year).stream().collect(Collectors.groupingBy(m -> m.getMonth()));
            List<Object> monthlyDataList = new ArrayList<>();
            for (Integer month : groupByMonthData.keySet()) {
                {
                    List<RegistrationEntity> registrationEntities = groupByMonthData.get(month);
                    Map<String, Object> modifiedGroupByMonthData = new HashMap<>();
                    modifiedGroupByMonthData.put("month", RegistrationUtility.getMonth(month));

                    Map<String, List<RegistrationEntity>> groupByFuelType = registrationEntities.stream().collect(Collectors.groupingBy(m -> m.getFuelType()));

                    List<Object> fuelTypeStatList = new ArrayList<>();
                    for (String fuelType : groupByFuelType.keySet()) {
                        Map<String, Object> fuelTypeMap = new HashMap<>();
                        Double volume = 0.0;
                        Double averagePrice = 0.0;
                        Double totalPrice = 0.0;

                        List<RegistrationEntity> fuelTypeRegistrationEntityList = groupByFuelType.get(fuelType);
                        for (RegistrationEntity registrationEntity : fuelTypeRegistrationEntityList) {
                            volume += registrationEntity.getVolume();
                            totalPrice += registrationEntity.getVolume() * registrationEntity.getPricePerLiter();
                        }
                        if (volume != 0) {
                            averagePrice = totalPrice / volume;
                        }

                        fuelTypeMap.put("fuelType", fuelType);
                        fuelTypeMap.put("volume", volume);
                        fuelTypeMap.put("averagePrice", averagePrice);
                        fuelTypeMap.put("totalPrice", totalPrice);

                        fuelTypeStatList.add(fuelTypeMap);

                    }
                    modifiedGroupByMonthData.put("data", fuelTypeStatList);
                    monthlyDataList.add(modifiedGroupByMonthData);
                }
            }

            yearData.put("year", year);
            yearData.put("records", monthlyDataList);

            responseListObject.add(yearData);
        }
        return responseListObject;
    }

    public Object getAllR(JSONObject data) {

        Integer givenYear = (Integer) data.get("year");
        List<RegistrationEntity> registrationEntityList = null;
        if (givenYear != null) {
            registrationEntityList = registrationRepository.findByYearEquals(givenYear);
        } else {

            registrationEntityList = registrationRepository.findAll();
        }
        Map<Integer, List<RegistrationEntity>> groupByYear = registrationEntityList.stream().collect(Collectors.groupingBy(w -> w.getYear()));


        List<Object> responseListObject = new ArrayList<>();

        Map<Integer, Object> responseData = new HashMap<Integer, Object>();

        for (Integer year : groupByYear.keySet()) {

            Map<String, Object> yearData = new HashMap<>();
            List<Object> monthlyCostList = new ArrayList<>();

            yearData.put("year", year);

            Map<Integer, List<RegistrationEntity>> groupByMonthData = groupByYear.get(year).stream().collect(Collectors.groupingBy(m -> m.getMonth()));
            Map<String, Double> monthlyPriceData = new HashMap<>();
            for (Integer month : groupByMonthData.keySet()) {
                {
                    List<RegistrationEntity> registrationEntities = groupByMonthData.get(month);
                    Double monthlyTotalCost = 0.0;
                    for (RegistrationEntity registrationEntity : registrationEntities) {
                        monthlyTotalCost += registrationEntity.getPricePerLiter() * registrationEntity.getVolume();
                    }
                    monthlyPriceData.put(RegistrationUtility.getMonth(month), monthlyTotalCost);
                    Map<String, Object> monthData = new HashMap<>();
                    monthData.put("month", RegistrationUtility.getMonth(month));
                    monthData.put("totalCost", monthlyTotalCost);

                    monthlyCostList.add(monthData);

                }
            }

            responseData.put(year, monthlyPriceData);

            yearData.put("costData", monthlyCostList);

            responseListObject.add(yearData);
        }
        return responseListObject;
    }

}
