package com.forhadmethun.fuelconsumptionmanagement.registration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RegistrationRepository extends JpaRepository<RegistrationEntity,Long> {
    RegistrationEntity findByIdEquals(Long id);
    List<RegistrationEntity> findByYearEquals(Integer year);
    List<RegistrationEntity> findByYearEqualsAndMonthEquals(Integer year,Integer months);

}
