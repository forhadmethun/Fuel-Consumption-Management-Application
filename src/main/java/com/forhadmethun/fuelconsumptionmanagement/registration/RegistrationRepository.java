package com.forhadmethun.fuelconsumptionmanagement.registration;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegistrationRepository extends JpaRepository<RegistrationEntity,Long> {
//    List<RegistrationEntity> findByDateEndingWith(String time);
//    List<RegistrationEntity> findByDateStartingWith(String time);
    List<RegistrationEntity> findByYearEquals(Integer year);
    List<RegistrationEntity> findByYearEqualsAndMonthEquals(Integer year,Integer months);

}
