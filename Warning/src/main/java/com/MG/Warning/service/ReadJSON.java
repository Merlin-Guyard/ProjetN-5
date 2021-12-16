package com.MG.Warning.service;

import com.MG.Warning.model.Persons;
import com.MG.Warning.model.Firestations;
import com.MG.Warning.model.MedicalRecords;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class ReadJSON {

    @EventListener(ApplicationReadyEvent.class)
    public List<Persons> ReadJSONPersons() {
        JSONParser jsonParser = new JSONParser();
        try {
            ObjectMapper mapper = new ObjectMapper();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("src/main/resources/data.json"));

            List<Persons> persons = Arrays.asList(mapper.readValue(jsonObject.get("persons").toString(), Persons[].class));

            persons.forEach(System.out::println);

            return persons;

        } catch (Exception ex) {
            System.out.println("Failed to read Persons");
            ex.printStackTrace();
        }
        return null;
    }

    @EventListener(ApplicationReadyEvent.class)
    public List<Firestations> ReadJSONFirestations() {
        JSONParser jsonParser = new JSONParser();
        try {
            ObjectMapper mapper = new ObjectMapper();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("src/main/resources/data.json"));

            List<Firestations> firestations = Arrays.asList(mapper.readValue(jsonObject.get("firestations").toString(), Firestations[].class));

            firestations.forEach(System.out::println);

//            return firestations;

        } catch (Exception ex) {
            System.out.println("Failed to read Firestations");
            ex.printStackTrace();
        }
        return null;
    }

    @EventListener(ApplicationReadyEvent.class)
    public List<MedicalRecords> ReadJSONMedicalRecords() {
        JSONParser jsonParser = new JSONParser();
        try {
            ObjectMapper mapper = new ObjectMapper();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("src/main/resources/data.json"));

            List<MedicalRecords> medicalRecords = Arrays.asList(mapper.readValue(jsonObject.get("medicalRecords").toString(), MedicalRecords[].class));

            medicalRecords.forEach(System.out::println);

//            return medicalRecords;

        } catch (Exception ex) {
            System.out.println("Failed to read MedicalRecords");
            ex.printStackTrace();
        }
        return null;
    }
}
