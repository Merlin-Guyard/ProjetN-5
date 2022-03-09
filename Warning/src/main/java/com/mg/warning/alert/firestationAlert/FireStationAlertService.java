package com.mg.warning.alert.firestationAlert;

import com.mg.warning.alert.AlertService;
import com.mg.warning.firestation.Firestation;
import com.mg.warning.firestation.FirestationRepository;
import com.mg.warning.medicalRecord.MedicalRecord;
import com.mg.warning.medicalRecord.MedicalRecordRepository;
import com.mg.warning.person.Person;
import com.mg.warning.person.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FireStationAlertService {

    @Autowired
    private FirestationRepository firestationRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @Autowired
    private AlertService alertService;

    Logger logger = LoggerFactory.getLogger(FireStationAlertService.class);

    public FirestationAlertWithNbDTO getFirestationAlertDTOWithSum(int stationNumber){

        List<Firestation> fireStations = firestationRepository.findByStationNumber(stationNumber);
        List<Person> persons = new ArrayList<>();


        FirestationAlertWithNbDTO dtoWithSum  = new FirestationAlertWithNbDTO();
        List<MedicalRecord> medicalRecords = new ArrayList<>();
        int adult = 0;
        int children = 0;

        //Get all persons from firestation's address
        for(Firestation firestation: fireStations)  {
            persons.addAll(personRepository.findByAddress(firestation.getAddress()));
        }

        //Get only interested data
        List<FirestationAlertDTO> dtoFirestationList  = new ArrayList<>();
        for (Person person : persons){
            FirestationAlertDTO dtoFirestation = new FirestationAlertDTO();
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

        logger.info("getFirestationAlertDTOWithSum executed successfully");
        return dtoWithSum;
    }
}
