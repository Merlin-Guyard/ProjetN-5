package com.MG.Warning;


import com.MG.Warning.dao.FirestationRepository;
import com.MG.Warning.dao.MedicalRecordRepository;
import com.MG.Warning.dao.PersonRepository;
import com.MG.Warning.model.JsonModel;
import com.MG.Warning.service.ReadJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.io.IOException;

@SpringBootApplication
public class WarningApplication {

	@Autowired
	private ReadJSON readJSON;

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private MedicalRecordRepository medicalRecordRepository;

	@Autowired
	private FirestationRepository firestationRepository;

	public static void main(String[] args) {
		SpringApplication.run(WarningApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void Initialize() throws IOException {
		JsonModel entities = readJSON.readEntities();
		entities.getPersons().forEach(person -> personRepository.save(person));
		entities.getMedicalrecords().forEach(medicalRecord -> medicalRecordRepository.save(medicalRecord));
		entities.getFirestations().forEach(firestation -> firestationRepository.save(firestation));
	}



}
