package com.mg.warning.medicalRecord;

import com.mg.warning.medicalRecord.MedicalRecordRepository;
import com.mg.warning.medicalRecord.MedicalRecord;
import com.mg.warning.person.Person;
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

    @PostMapping(value= "")
    public void postOneMedicalRecord(@RequestBody MedicalRecord medicalRecord){
        medicalRecordRepository.save(medicalRecord);
    }

    @PutMapping(value= "")
    public void updateOneMedicalRecord(@RequestBody MedicalRecord medicalRecord){medicalRecordRepository.update(medicalRecord);}

    @DeleteMapping(value= "")
    public void delOneMedicalRecord(@PathVariable("firstname") String firstname, @PathVariable("lastname") String lastname){
        medicalRecordRepository.delete(firstname, lastname);
    }
}
