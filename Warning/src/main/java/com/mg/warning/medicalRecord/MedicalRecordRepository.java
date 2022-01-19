package com.mg.warning.medicalRecord;


import com.mg.warning.firestation.Firestation;
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

    //GET BY NAME
    //TODO -> MedicalRecord findByName(String firstname, String lastname) ou Optional<MedicalRecord> findByName(String firstname, String lastname)
    public List<MedicalRecord> findByName(String firstname, String lastname) {
        List<MedicalRecord> result = new ArrayList<>();
        for(MedicalRecord medicalRecord: medicalRecordList)  {
            if(medicalRecord.getFirstName().equals(firstname) && medicalRecord.getLastName().equals(lastname)) {
                result.add(medicalRecord);
            }
        }
        return result;
    }
}
