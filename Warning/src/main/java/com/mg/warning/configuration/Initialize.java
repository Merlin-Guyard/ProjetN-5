package com.mg.warning.service;

import com.mg.warning.alert.fire.FireService;
import com.mg.warning.firestation.FirestationRepository;
import com.mg.warning.json.JsonModel;
import com.mg.warning.json.ReadJSON;
import com.mg.warning.medicalRecord.MedicalRecordRepository;
import com.mg.warning.person.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.tinylog.Logger;

import java.io.IOException;

@Component
public class Initialize {

    @Autowired
    private ReadJSON readJSON;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @Autowired
    private FirestationRepository firestationRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void saveData() throws IOException {
        JsonModel entities = readJSON.readEntities();
        entities.getPersons().forEach(person -> personRepository.save(person));
        entities.getMedicalrecords().forEach(medicalRecord -> medicalRecordRepository.save(medicalRecord));
        entities.getFirestations().forEach(firestation -> firestationRepository.save(firestation));
        Logger.info("Entities written successfully");
    }
}
