package com.mg.warning.alert;

import com.mg.warning.medicalRecord.MedicalRecord;
import org.springframework.stereotype.Component;
import org.tinylog.Logger;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Component
public class AlertService {

    public int getAgeFromMedicalRecords (List<MedicalRecord> medicalRecordList, String firstname, String lastname){

        for (MedicalRecord medicalRecord : medicalRecordList) {
            if (medicalRecord.getFirstName().equals(firstname) && medicalRecord.getLastName().equals(lastname)){
                int result = Period.between(LocalDate.parse(medicalRecord.getBirthdate(), DateTimeFormatter.ofPattern("MM/dd/yyyy")), LocalDate.now()).getYears();
                Logger.info("getAgeFromMedicalRecords for {}, {} executed successfully", firstname,lastname);
                return result;
            }
        }
        Logger.error("Can't get age from medicalRecords");
        return 0;
    }
}