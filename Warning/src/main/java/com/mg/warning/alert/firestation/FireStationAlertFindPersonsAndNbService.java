package com.mg.warning.alert.firestation;

import com.mg.warning.alert.AlertService;
import com.mg.warning.firestation.Firestation;
import com.mg.warning.firestation.FirestationRepository;
import com.mg.warning.medicalRecord.MedicalRecord;
import com.mg.warning.medicalRecord.MedicalRecordRepository;
import com.mg.warning.person.Person;
import com.mg.warning.person.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class FireStationAlertFindPersonsAndNbService {

    @Autowired
    private FirestationRepository firestationRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @Autowired
    private AlertService alertService;

    public FirestationAlertWithNbDTO getFirestationAlertDTOWithSum(int stationNumber){

        List<Firestation> fireStations = firestationRepository.findByStationNumber(stationNumber);
        List<Person> persons = new ArrayList<>();
        FirestationAlertDTO dtoFirestation = new FirestationAlertDTO();
        List<FirestationAlertDTO> dtoFirestationList  = new ArrayList<>();
        FirestationAlertWithNbDTO dtoWithSum  = new FirestationAlertWithNbDTO();
        List<MedicalRecord> medicalRecords = new ArrayList<>();
        int adult = 0;
        int children = 0;

        //Get all persons from firestation's address
        for(Firestation firestation: fireStations)  {
            persons.addAll(personRepository.findByAddress(firestation.getAddress()));
        }

        //Get only interested data
        for (Person person : persons){
            dtoFirestation.setFirstname(person.getFirstName());
            dtoFirestation.setLastname(person.getLastName());
            dtoFirestation.setAddress(person.getAddress());
            dtoFirestation.setPhone(person.getPhone());
            dtoFirestationList.add(dtoFirestation);
        }
        dtoWithSum.setFirestationAlertDTOS(dtoFirestationList);

        //Get all medical records from persons
        for(Person person: persons)  {
            medicalRecords.add(medicalRecordRepository.findByName(person.getFirstName(), person.getLastName()));
        }

        //Check if minor or major using age
        for(MedicalRecord medicalRecord: medicalRecords)  {
            if (alertService.getAgeFromMedicalRecords(medicalRecords, medicalRecord.getFirstName(), medicalRecord.getLastName()) >=18){
                adult++;
            } else if (alertService.getAgeFromMedicalRecords(medicalRecords, medicalRecord.getFirstName(), medicalRecord.getLastName()) <18){
                children++;
            }
        }

        dtoWithSum.setNbAdults(adult);
        dtoWithSum.setNbChildrens(children);
        return dtoWithSum;
    }
}
