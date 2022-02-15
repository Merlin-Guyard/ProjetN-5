package com.mg.warning.medicalRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.tinylog.Logger;

import java.util.List;

@RestController
@RequestMapping("/medicalRecord")
public class MedicalRecordController {

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @GetMapping(value = "/GetAll")
    public List<MedicalRecord> getAllMedicalRecords() {
        Logger.info("/medicalRecord/GetAll function get all called");
        return medicalRecordRepository.findAll();
    }

    @PostMapping(value = "")
    public void postOneMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        Logger.info("/medicalRecord function post called for {}, {}", medicalRecord.getFirstName(), medicalRecord.getLastName());
        medicalRecordRepository.save(medicalRecord);
    }

    @PutMapping(value = "")
    public void updateOneMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        Logger.info("/medicalRecord function update called for {}, {}", medicalRecord.getFirstName(), medicalRecord.getLastName());
        medicalRecordRepository.update(medicalRecord);
    }

    @DeleteMapping(value = "")
    public void delOneMedicalRecord(@PathVariable("firstname") String firstname, @PathVariable("lastname") String lastname) {
        Logger.info("/medicalRecord function delete called for {}, {}", firstname, lastname);
        medicalRecordRepository.delete(firstname, lastname);
    }
}
