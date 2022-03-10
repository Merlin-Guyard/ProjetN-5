package com.mg.warning.alert;

import com.mg.warning.medicalRecord.MedicalRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Component
public class AlertService {

    Logger logger = LoggerFactory.getLogger(AlertService.class);

    public int getAgeFromMedicalRecords(List<MedicalRecord> medicalRecordList, String firstname, String lastname) {

        for (MedicalRecord medicalRecord : medicalRecordList) {
            if (medicalRecord.getFirstName().equals(firstname) && medicalRecord.getLastName().equals(lastname)) {
                logger.debug("getting age for {}, {}", firstname, lastname);
                int result = Period.between(LocalDate.parse(medicalRecord.getBirthdate(), DateTimeFormatter.ofPattern("MM/dd/yyyy")), LocalDate.now()).getYears();
                return result;
            }
        }
        logger.error("Can't get age from medicalRecords");
        return 0;
    }
}