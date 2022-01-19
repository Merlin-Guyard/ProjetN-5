package com.mg.warning.medicalRecord;

import com.mg.warning.medicalRecord.MedicalRecordRepository;
import com.mg.warning.medicalRecord.MedicalRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/medicalRecord")
public class MedicalRecordController {

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @GetMapping(value= "/GetAll")
    public List<MedicalRecord> getAllMedicalRecords(){
        return medicalRecordRepository.findAll();
    }
}
