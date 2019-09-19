package com.forhadmethun.fuelconsumptionmanagement.registration;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RegistrationUtility {
    public static Boolean validate(List<RegistrationDTO> registrationDTOS, Map<String, String> message){
        for(RegistrationDTO registrationDTO: registrationDTOS){
            if(registrationDTO.getFuelType() == null){
                message.put("error", "Bad Request");
                message.put("message",  "Fuel Type cannot be empty" ) ;
                return false;
            }
            if(registrationDTO.getPricePerLiter() == null){
                message.put("error", "Bad Request");
                message.put("message",  "Price per liter cannot be empty" ) ;
                return false;
            }
            if(registrationDTO.getVolume() == null){
                message.put("error", "Bad Request");
                message.put("message",  "Volume cannot be empty" ) ;
                return false;
            }
            if(registrationDTO.getDate() == null){
                message.put("error", "Bad Request");
                message.put("message",  "Date cannot be empty" ) ;
                return false;
            }
            if(registrationDTO.getDriverId() == null){
                message.put("error", "Bad Request");
                message.put("message",  "Driver Id cannot be empty" ) ;
                return false;
            }
            String[] date = registrationDTO.getDate().split("\\.");
            if(date.length < 3){
                message.put("error", "Bad Request");
                message.put("message",  "Date Should in the format of: MM.DD.YYY, Ex: 01.21.2018" ) ;
                return false;
            }
            if(date.length == 3){
                if(!isInteger(date[0]) || !isInteger(date[1])||  !isInteger(date[2])){
                    message.put("error", "Bad Request");
                    message.put("message",  "Incorrect Date Format! Date Should in the format of: MM.DD.YYYY, Ex: 01.21.2018" ) ;
                    return false;
                }
                if(Integer.parseInt(date[0])>12 || Integer.parseInt(date[0]) <= 0){
                    message.put("error", "Bad Request");
                    message.put("message",  "Please Enter Month Correctly! Date Should in the format of: MM.DD.YYYY, Ex: 01.21.2018" ) ;
                    return false;
                }
                if(Integer.parseInt(date[1])>31 || Integer.parseInt(date[1]) <= 0){
                    message.put("message",  "Please Enter Day Correctly! Date Should in the format of: MM.DD.YYYY, Ex: 01.21.2018" ) ;
                    return false;
                }
                if(date[2].length() < 4){
                    message.put("error", "Bad Request");
                    message.put("message",  "Please Enter Year Correctly! Date Should in the format of: MM.DD.YYYY, Ex: 01.21.2018" ) ;
                    return false;
                }

            }

        }
        return true;
    }

    public static RegistrationDTO getRegistrationDTOFromRegistrationEntity(RegistrationEntity registrationEntity){
        return new RegistrationDTO(
                registrationEntity.getId(),
                registrationEntity.getFuelType(),
                registrationEntity.getPricePerLiter(),
                registrationEntity.getVolume(),
                registrationEntity.getDriverId(),
                registrationEntity.getDay()+ "." + registrationEntity.getMonth()+ "." + registrationEntity.getYear()
        );
    }

    public static RegistrationEntity getRegistrationEntityFromRegistrationDTO(RegistrationDTO registrationDTO){
        return new RegistrationEntity(
                0,
                registrationDTO.getFuelType(),
                registrationDTO.getPricePerLiter(),
                registrationDTO.getVolume(),
                registrationDTO.getDriverId(),
                Integer.valueOf(registrationDTO.getDate().split("\\.")[0]),
                Integer.valueOf(registrationDTO.getDate().split("\\.")[1]),
                Integer.valueOf(registrationDTO.getDate().split("\\.")[2])
        );
    }

    public static List<RegistrationDTO> getRegistrationDTOListFromRegistrationEntityList(List<RegistrationEntity> allRegistrationEntity){
        List<RegistrationDTO> registrationDTOList = new ArrayList<>();
        for(RegistrationEntity registrationEntity: allRegistrationEntity){
            registrationDTOList.add(RegistrationUtility.getRegistrationDTOFromRegistrationEntity(registrationEntity));
        }
        return registrationDTOList;
    }

    public static List<RegistrationEntity> getRegistrationEntityListFromRegistrationDTOList(List<RegistrationDTO> registrationDTOList){
        List<RegistrationEntity> registrationEntityList = new ArrayList<>();
        for(RegistrationDTO registrationEntity: registrationDTOList){
            registrationEntityList.add(RegistrationUtility.getRegistrationEntityFromRegistrationDTO(registrationEntity));
        }
        return registrationEntityList;
    }

    public static String getMonth(int month) {
        return new DateFormatSymbols().getMonths()[month-1];
    }
    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        } catch(NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }
    public static boolean isDouble(String s){
        try
        {
            Double.parseDouble(s);
        }
        catch(NumberFormatException e) {
            return false;
        } catch(NullPointerException e) {
            return false;
        }
        return true;
    }

}