package com.mg.warning.service;

import com.mg.warning.firestation.Firestation;
import com.mg.warning.firestation.FirestationRepository;
import com.mg.warning.medicalRecord.MedicalRecord;
import com.mg.warning.medicalRecord.MedicalRecordRepository;
import com.mg.warning.person.Person;
import com.mg.warning.person.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CheckHealthIndicator implements HealthIndicator {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @Autowired
    private FirestationRepository firestationRepository;

    @Override
    public Health health() {
        List<Person> persons = personRepository.findAll();
        List<Firestation> fireStations = firestationRepository.findAll();
        List<MedicalRecord> medicalRecords = medicalRecordRepository.findAll();

        Health.Builder status = Health.up();
        if (persons.isEmpty() || fireStations.isEmpty() || medicalRecords.isEmpty()) {
            status = Health.down();
        }
        return status.build();
    }
}