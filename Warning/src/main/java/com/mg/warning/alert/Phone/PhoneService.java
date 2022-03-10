package com.mg.warning.alert.Phone;

import com.mg.warning.firestation.Firestation;
import com.mg.warning.firestation.FirestationRepository;
import com.mg.warning.person.Person;
import com.mg.warning.person.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tinylog.Logger;

import java.util.ArrayList;
import java.util.List;


@Service
public class PhoneService {

    @Autowired
    private FirestationRepository firestationRepository;

    @Autowired
    private PersonRepository personRepository;

    public List<PhoneDTO> getPhoneDTO(int stationNumber) {

        //Get all persons from firestation's address
        List<Person> persons = getPersons(stationNumber);

        //Get and write phone numbers
        List<PhoneDTO> dtoPhoneList = getPhoneDTOS(persons);

        Logger.info("getPhoneDTO executed successfully");
        return dtoPhoneList;
    }

    private List<PhoneDTO> getPhoneDTOS(List<Person> persons) {
//        Logger.debug("getting persons");
        List<PhoneDTO> dtoPhoneList = new ArrayList<>();
        for (Person person : persons) {
            PhoneDTO dtoPhone = new PhoneDTO();
            dtoPhone.setPhone(person.getPhone());
            dtoPhoneList.add(dtoPhone);
        }
        return dtoPhoneList;
    }

    private List<Person> getPersons(int stationNumber) {
//        Logger.debug("getting and writing numbers");
        List<Person> persons = new ArrayList<>();
        List<Firestation> fireStations = firestationRepository.findByStationNumber(stationNumber);
        for (Firestation firestation : fireStations) {
            persons.addAll(personRepository.findByAddress(firestation.getAddress()));
        }
        return persons;
    }
}