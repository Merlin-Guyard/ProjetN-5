package com.MG.Warning.controller;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.FileReader;


//import org.json.simple.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


@Component
public class Util {

    @EventListener(ApplicationReadyEvent.class)
    public void ReadJSONFile() {
        JSONParser jsonParser = new JSONParser();

        try {

            JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("src/main/resources/data.json"));
            JSONArray json_AllPersons = jsonObject.getJSONArray("persons");
//TODO
//            JSONArray json_AllFirestations = jsonObject.getJSONArray("firestations");
//            JSONArray json_AllMedicalRecords = jsonObject.getJSONArray("medicalrecords");
            int length = json_AllPersons.length();
            for(int i=0; i<length; i++) {
                JSONObject json_Person = json_AllPersons.getJSONObject(i);
                System.out.println("name :" + json_Person.getString("firstName"));
            }



//            JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("src/main/resources/data.json"));
//            System.out.println("persons: "+jsonObject.get("persons"));
//            String stringPersons = jsonObject.get("persons").toString();


// https://attacomsian.com/blog/jackson-read-json-file
//            ObjectMapper mapper = new ObjectMapper();


//            List<Persons> persons = Arrays.asList(mapper.readValue(stringPersons, Persons.class));
//              Persons persons = mapper.readValue(stringPersons, Persons.class);


//            persons.forEach(System.out::println);
//              System.out.println(persons);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
