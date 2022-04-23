package com.mg.warning.service;

import com.mg.warning.dto.FirestationAlertDTO;
import com.mg.warning.dto.FirestationAlertWithNbDTO;
import com.mg.warning.model.Firestation;
import com.mg.warning.repository.FirestationRepository;
import com.mg.warning.model.MedicalRecord;
import com.mg.warning.repository.MedicalRecordRepository;
import com.mg.warning.model.Person;
import com.mg.warning.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tinylog.Logger;

import java.util.ArrayList;
import java.util.List;

@Service
public class FirestationAlertService {

    @Autowired
    private FirestationRepository firestationRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;


    public FirestationAlertWithNbDTO getFirestationAlertDTOWithSum(int stationNumber){

        FirestationAlertWithNbDTO dtoWithSum  = new FirestationAlertWithNbDTO();

        //Get persons from firestation's address
        List<Person> persons = getPersons(stationNumber);

        //Get only interested data
        List<FirestationAlertDTO> dtoFirestationList = getFirestationDTOS(persons);


        //Get medicalrecords from persons
        List<MedicalRecord> medicalRecords = getMedicalRecords(persons);

        //Check if minor or major using age
        Logger.debug("check age status");
        int adult = 0;
        int children = 0;
        for(MedicalRecord medicalRecord: medicalRecords)  {
            if (medicalRecord.getAgeFromMedicalRecords(medicalRecords, medicalRecord.getFirstName(), medicalRecord.getLastName()) >=18){
                adult++;
            } else if (medicalRecord.getAgeFromMedicalRecords(medicalRecords, medicalRecord.getFirstName(), medicalRecord.getLastName()) <18){
                children++;
            }
        }

        dtoWithSum.setFirestationAlertDTOS(dtoFirestationList);
        dtoWithSum.setNbAdults(adult);
        dtoWithSum.setNbChildrens(children);

        Logger.info("getFirestationAlertDTOWithSum executed successfully");
        return dtoWithSum;
    }

    private List<MedicalRecord> getMedicalRecords(List<Person> persons) {
        Logger.debug("find medicalrecords from persons");
        List<MedicalRecord> medicalRecords = new ArrayList<>();
        for(Person person: persons)  {
            medicalRecords.add(medicalRecordRepository.findByName(person.getFirstName(), person.getLastName()));
        }
        return medicalRecords;
    }

    private List<FirestationAlertDTO> getFirestationDTOS(List<Person> persons) {
        Logger.debug("get persons");
        List<FirestationAlertDTO> dtoFirestationList  = new ArrayList<>();
        for (Person person : persons){
            FirestationAlertDTO dtoFirestation = new FirestationAlertDTO();
            dtoFirestation.setFirstname(person.getFirstName());
            dtoFirestation.setLastname(person.getLastName());
            dtoFirestation.setAddress(person.getAddress());
            dtoFirestation.setPhone(person.getPhone());
            dtoFirestationList.add(dtoFirestation);
        }
        return dtoFirestationList;
    }

    private List<Person> getPersons(int stationNumber) {
        Logger.debug("find persons from firestation");
        List<Firestation> fireStations = firestationRepository.findByStationNumber(stationNumber);
        List<Person> persons = new ArrayList<>();
        for(Firestation firestation: fireStations)  {
            persons.addAll(personRepository.findByAddress(firestation.getAddress()));
        }
        return persons;
    }
}
