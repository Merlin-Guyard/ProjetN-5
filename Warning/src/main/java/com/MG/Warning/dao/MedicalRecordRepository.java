package com.MG.Warning.dao;


import com.MG.Warning.model.MedicalRecord;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MedicalRecordRepository {

    // CRUD

    private List<MedicalRecord> medicalRecordList = new ArrayList<>();

    public void save(MedicalRecord medicalRecord) {
        medicalRecordList.add(medicalRecord);
    }

    public List<MedicalRecord> findAll() {
        return medicalRecordList;
    }
}
