package com.mg.warning.controller;

import com.mg.warning.dao.FirestationRepository;
import com.mg.warning.dao.PersonRepository;
import com.mg.warning.model.Firestation;
import com.mg.warning.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/firestation")
public class FireStationController {

    @Autowired
    private FirestationRepository firestationRepository;

   @Autowired
    private PersonRepository personRepository;

    @GetMapping(value= "/GetAll")
    public List<Firestation> getAllFirestation(){
        return firestationRepository.findAll();
    }

    @GetMapping(value= "")
    public PersonsWithSumDTO getAllFirestation(@RequestParam("stationNumber") int stationNumber){
        //Optional<Firestation> optFirestation = firestationRepository.findById(stationNumber);
        //List<Person> persons = optFirestation.stream().flatMap(firestation -> toPersons(firestation)).collect(Collectors.toList())
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
