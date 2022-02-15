package com.mg.warning.alert.PhoneAlert;

import com.mg.warning.firestation.Firestation;
import com.mg.warning.firestation.FirestationRepository;
import com.mg.warning.person.Person;
import com.mg.warning.person.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinylog.Logger;

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
        List<PhoneAlertDTO> dtoPhoneList = new ArrayList<>();

        //Get all persons from firestation's address
        for(Firestation firestation: fireStations)  {
            persons.addAll(personRepository.findByAddress(firestation.getAddress()));
        }

        for (Person person : persons){
            PhoneAlertDTO dtoPhone = new PhoneAlertDTO();
            dtoPhone.setPhone(person.getPhone());
            dtoPhoneList.add(dtoPhone);
        }

        Logger.info("getPersonInfoDTO executed successfully");
        return dtoPhoneList;
    }
}