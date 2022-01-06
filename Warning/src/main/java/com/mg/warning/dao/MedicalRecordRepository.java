package com.mg.warning.dao;


import com.mg.warning.model.MedicalRecord;
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
            for (MedicalRecord m : medicalRecordList) {
                if (m.getFirstName().equals(medicalRecord.getFirstName()) && m.getLastName().equals(medicalRecord.getLastName())) {
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
            medicalRecordList.removeIf(m -> m.getFirstName().equals(firstname) && m.getLastName().equals(lastname));
        }
    }
