package com.mg.warning.alert.phoneAlert;

import com.mg.warning.firestation.Firestation;
import com.mg.warning.firestation.FirestationRepository;
import com.mg.warning.person.Person;
import com.mg.warning.person.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class PhoneAlertService {

    @Autowired
    private FirestationRepository firestationRepository;

    @Autowired
    private PersonRepository personRepository;

    public List<PhoneAlertDTO> getPhoneDTO(int stationNumber) {
        List<Firestation> fireStations = firestationRepository.findByStationNumber(stationNumber);
        List<Person> persons = new ArrayList<>();
        PhoneAlertDTO dtoPhone = new PhoneAlertDTO();
        List<PhoneAlertDTO> dtoPhoneList = new ArrayList<>();

        //Get all persons from firestation's address
        for(Firestation firestation: fireStations)  {
            persons.addAll(personRepository.findByAddress(firestation.getAddress()));
        }

        for (Person person : persons){
            dtoPhone.setPhone(person.getPhone());
            dtoPhoneList.add(dtoPhone);
        }

        return dtoPhoneList;
    }
}