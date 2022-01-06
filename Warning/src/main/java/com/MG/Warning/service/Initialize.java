package com.mg.warning.service;

import com.mg.warning.dao.FirestationRepository;
import com.mg.warning.dao.MedicalRecordRepository;
import com.mg.warning.dao.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.io.IOException;

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
        com.mg.warning.model.JsonModel entities = readJSON.readEntities();
        entities.getPersons().forEach(person -> personRepository.save(person));
        entities.getMedicalrecords().forEach(medicalRecord -> medicalRecordRepository.save(medicalRecord));
        entities.getFirestations().forEach(firestation -> firestationRepository.save(firestation));
    }
}
