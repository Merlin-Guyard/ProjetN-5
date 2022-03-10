package com.mg.warning.alert.fire;

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
public class FireService {


    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @Autowired
    private FirestationRepository firestationRepository;


    public FireDTO getFireDTO(String address) {
        FireDTO dtoFire = new FireDTO();

        //get data
        Logger.debug("getting data");
        List<FirePersonDTO> dtoFirePersonList = getFirePersonDTOS(address);
        List<FireFireStationDTO> dtoFireStationList = getFireStationDTOS(address);

        //write data
        Logger.debug("writing data");
        dtoFire.setFireAlertPersonsDTO(dtoFirePersonList);
        dtoFire.setFireAlertStationDTO(dtoFireStationList);


        Logger.info("getFireDTO executed successfully");
        return dtoFire;
    }

    private List<FirePersonDTO> getFirePersonDTOS(String address) {
        Logger.debug("getting persons");
        List<FirePersonDTO> dtoFirePersonList = new ArrayList<>();
        List<Person> persons = new ArrayList<>(personRepository.findByAddress(address));
        List<MedicalRecord> medicalRecords = new ArrayList<>(medicalRecordRepository.findAll());

        for (Person person : persons) {
            FirePersonDTO dtoFirePerson = new FirePersonDTO();
            dtoFirePerson.setLastName(person.getLastName());
            dtoFirePerson.setPhone(person.getPhone());
            for (MedicalRecord medicalRecord : medicalRecords) {
                if (medicalRecord.getFirstName().equals(person.getFirstName()) && medicalRecord.getLastName().equals(person.getLastName())) {
                    dtoFirePerson.setAge(medicalRecord.getAgeFromMedicalRecords(medicalRecords, person.getFirstName(), person.getLastName()));
                    dtoFirePerson.setMedications(medicalRecord.getMedications());
                    dtoFirePerson.setAllergies(medicalRecord.getAllergies());
                }
            }
            dtoFirePersonList.add(dtoFirePerson);
        }
        return dtoFirePersonList;
    }

    private List<FireFireStationDTO> getFireStationDTOS(String address) {
        Logger.debug("getting firestations");
        List<FireFireStationDTO> dtoFireStationList = new ArrayList<>();
        List<Firestation> firestations = new ArrayList<>(firestationRepository.findByAddress(address));

        for (Firestation firestation : firestations) {
            FireFireStationDTO dtoFireStation = new FireFireStationDTO();
            dtoFireStation.setStation(firestation.getStation());
            dtoFireStationList.add(dtoFireStation);
        }
        return dtoFireStationList;
    }
}
