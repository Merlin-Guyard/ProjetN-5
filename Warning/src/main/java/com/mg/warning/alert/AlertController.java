package com.mg.warning.alert;

import com.mg.warning.firestation.Firestation;
import com.mg.warning.firestation.FirestationRepository;
import com.mg.warning.person.Person;
import com.mg.warning.person.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AlertController {

    @Autowired
    private FirestationRepository firestationRepository;

    @Autowired
    private PersonRepository personRepository;


    @GetMapping(value= "/firestation")
    public PersonsWithSumDTO getAllFirestation(@RequestParam("stationNumber") int stationNumber){
        List<Firestation> fireStations = firestationRepository.findById(stationNumber);
        List<Person> persons = new ArrayList<>();
        for(Firestation firestation: fireStations)  {
            persons.addAll(personRepository.findByAddress(firestation.getAddress()));
        }

        PersonsWithSumDTO dto  = new PersonsWithSumDTO();
        dto.setPersons(persons);
        dto.setNbAdults(0);
        dto.setNbChildren(0);
        return dto;
    }
}
