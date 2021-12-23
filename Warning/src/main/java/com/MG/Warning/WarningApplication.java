package com.MG.Warning;


import com.MG.Warning.dao.PersonRepository;
import com.MG.Warning.model.JsonModel;
import com.MG.Warning.service.ReadJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;

import java.io.IOException;

@SpringBootApplication
public class WarningApplication {

	@Autowired
	private ReadJSON readJSON;

	@Autowired
	private PersonRepository personRepository;

	public static void main(String[] args) {
		SpringApplication.run(WarningApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() throws IOException {
		JsonModel entities = readJSON.readEntities();
		entities.getPersons().forEach( persons -> personRepository.save(persons));
	}



}
