package com.mg.warning.alert.firestation;

import com.mg.warning.firestation.Firestation;
import com.mg.warning.firestation.FirestationRepository;
import com.mg.warning.medicalRecord.MedicalRecord;
import com.mg.warning.medicalRecord.MedicalRecordRepository;
import com.mg.warning.person.Person;
import com.mg.warning.person.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinylog.Logger;

import java.util.ArrayList;
import java.util.List;

@Component
public class FireStationService {

    @Autowired
    private FirestationRepository firestationRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;


    public FirestationWithNbDTO getFirestationAlertDTOWithSum(int stationNumber){

        FirestationWithNbDTO dtoWithSum  = new FirestationWithNbDTO();

        //Get persons from firestation's address
        List<Person> persons = getPersons(stationNumber);

        //Get only interested data
        List<FirestationDTO> dtoFirestationList = getFirestationDTOS(persons);


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

    private List<FirestationDTO> getFirestationDTOS(List<Person> persons) {
        Logger.debug("get persons");
        List<FirestationDTO> dtoFirestationList  = new ArrayList<>();
        for (Person person : persons){
            FirestationDTO dtoFirestation = new FirestationDTO();
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
