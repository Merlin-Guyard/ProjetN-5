package com.mg.warning.medicalRecord;


import com.mg.warning.medicalRecord.MedicalRecord;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MedicalRecordRepository {

    private List<MedicalRecord> medicalRecordList = new ArrayList<>();

    //GET
    public List<MedicalRecord> findAll() {
        return medicalRecordList;
    }

    //PUT
    public void update(MedicalRecord medicalRecord) {
        int index = 0;
        for (MedicalRecord medicalRecordLoop : medicalRecordList) {
            if (medicalRecordLoop.getFirstName().equals(medicalRecord.getFirstName()) && medicalRecordLoop.getLastName().equals(medicalRecord.getLastName())) {
                medicalRecordList.set(index, medicalRecord);
                index++;
            }
        }
    }

    //POST
    public void save(MedicalRecord medicalRecord) {
        medicalRecordList.add(medicalRecord);
    }

    //DELETE
    public void delete(String firstname, String lastname) {
        medicalRecordList.removeIf(medicalRecord -> medicalRecord.getFirstName().equals(firstname) && medicalRecord.getLastName().equals(lastname));
    }
}
