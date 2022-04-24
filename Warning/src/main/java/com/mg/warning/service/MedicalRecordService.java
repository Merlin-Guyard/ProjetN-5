package com.mg.warning.service;

import com.mg.warning.model.MedicalRecord;
import com.mg.warning.repository.MedicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalRecordService {

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    public List<MedicalRecord> findAll() {
        return medicalRecordRepository.findAll();
    }

    public void save(MedicalRecord medicalRecord) {
        medicalRecordRepository.save(medicalRecord);
    }

    public void update(MedicalRecord medicalRecord) {
        medicalRecordRepository.update(medicalRecord);
    }

    public void delete(String firstname, String lastname) {
        medicalRecordRepository.delete(firstname, lastname);
    }
}