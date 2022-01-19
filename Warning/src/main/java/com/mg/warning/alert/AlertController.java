package com.mg.warning.alert;

import com.mg.warning.alert.firestation.FirestationAlertDTO;
import com.mg.warning.alert.firestation.FirestationAlertDTOWithSum;
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
    public FirestationAlertDTOWithSum getAllFirestation(@RequestParam("stationNumber") int stationNumber){


        //question comment organiser
        List<Firestation> fireStations = firestationRepository.findById(stationNumber);
        List<Person> persons = new ArrayList<>();

        for(Firestation firestation: fireStations)  {
            persons.addAll(personRepository.findByAddress(firestation.getAddress()));
        }

        FirestationAlertDTO dto = new FirestationAlertDTO();
        List<FirestationAlertDTO> dtoList  = new ArrayList<>();
        FirestationAlertDTOWithSum dtoWithSum  = new FirestationAlertDTOWithSum();

        for (Person person : persons){
            dto.setFirstname(person.getFirstName());
            dto.setLastname(person.getLastName());
            dto.setAddress(person.getAddress());
            dto.setPhone(person.getPhone());
            dtoList.add(dto);
        }

        dtoWithSum.setFirestationAlertDTOS(dtoList);
        dtoWithSum.setNbAdults(0);
        dtoWithSum.setNbChildren(0);
        return dtoWithSum;
    }
}
