package com.MG.Warning.controller;

import com.MG.Warning.dao.MedicalRecordRepository;
import com.MG.Warning.model.MedicalRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
