package com.mg.warning.alert.fireAlert;

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
public class FireAlertService {

    Logger logger = LoggerFactory.getLogger(FireAlertService.class);

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @Autowired
    private FirestationRepository firestationRepository;

    @Autowired
    private AlertService alertService;

    public FireAlertDTO getFireDTO(String address) {


        List<Person> persons = new ArrayList<>(personRepository.findByAddress(address));
        List<MedicalRecord> medicalRecords = new ArrayList<>(medicalRecordRepository.findAll());
        List<Firestation> firestations = new ArrayList<>(firestationRepository.findByAddress(address));
        List<FireAlertPersonDTO> dtoFirePersonList = new ArrayList<>();
        List<FireAlertStationDTO> dtoFireStationList = new ArrayList<>();
        FireAlertDTO dtoFire = new FireAlertDTO();

        //Get only interested data
        for (Person person : persons) {
            FireAlertPersonDTO dtoFirePerson = new FireAlertPersonDTO();
            dtoFirePerson.setLastName(person.getLastName());
            dtoFirePerson.setPhone(person.getPhone());
            for (MedicalRecord medicalRecord : medicalRecords){
                if(medicalRecord.getFirstName().equals(person.getFirstName())&&medicalRecord.getLastName().equals(person.getLastName())){
                    dtoFirePerson.setAge(alertService.getAgeFromMedicalRecords(medicalRecords, person.getFirstName(), person.getLastName()));
                    dtoFirePerson.setMedications(medicalRecord.getMedications());
                    dtoFirePerson.setAllergies(medicalRecord.getAllergies());
                }
            }
            dtoFirePersonList.add(dtoFirePerson);
        }

        for(Firestation firestation : firestations){
            FireAlertStationDTO dtoFireStation = new FireAlertStationDTO();
            dtoFireStation.setStation(firestation.getStation());
            dtoFireStationList.add(dtoFireStation);
        }

        dtoFire.setFireAlertPersonsDTO(dtoFirePersonList);
        dtoFire.setFireAlertStationDTO(dtoFireStationList);

        logger.info("getFireDTO executed successfully");
        return dtoFire;
    }
}
