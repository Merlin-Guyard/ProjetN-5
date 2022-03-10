package com.mg.warning.alert.flood;

import com.mg.warning.firestation.Firestation;
import com.mg.warning.firestation.FirestationRepository;
import com.mg.warning.medicalRecord.MedicalRecord;
import com.mg.warning.medicalRecord.MedicalRecordRepository;
import com.mg.warning.person.Person;
import com.mg.warning.person.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tinylog.Logger;

import java.util.ArrayList;
import java.util.List;

@Service
public class FloodService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private FirestationRepository firestationRepository;

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;


    public List<FloodDTO> getFloodDTO(int[] stationNumber) {

        //get firestation from station number
        List<Firestation> fireStations = getFirestations(stationNumber);

        //get and write persons and medicalrecords
        List<FloodDTO> dtoFloodList = getFloodDTOS(fireStations);

        Logger.info("getFloodDTO executed successfully");
        return dtoFloodList;
    }

    private List<Firestation> getFirestations(int[] stationNumber) {
        Logger.debug("find firestations");
        List<Firestation> fireStations = new ArrayList<>();
        for (int i : stationNumber) {
            fireStations.addAll(firestationRepository.findByStationNumber(i));
        }
        return fireStations;
    }

    private List<FloodDTO> getFloodDTOS(List<Firestation> fireStations) {
        List<MedicalRecord> medicalRecords = medicalRecordRepository.findAll();
        List<Person> persons = new ArrayList<>();
        List<FloodDTO> dtoFloodList = new ArrayList<>();
        Logger.debug("get and write persons and medicalrecords");
        for (Firestation firestation : fireStations) {
            FloodDTO dtoFlood = new FloodDTO();
            List<FloodPersonsDTO> dtoFloodPersonList = new ArrayList<>();
            persons.addAll(personRepository.findByAddress(firestation.getAddress()));

            for (Person person : persons) {
                FloodPersonsDTO dtoFloodPerson = new FloodPersonsDTO();
                dtoFloodPerson.setLastName(person.getLastName());
                dtoFloodPerson.setPhone(person.getPhone());
                for (MedicalRecord medicalRecord : medicalRecords) {
                    if (medicalRecord.getFirstName().equals(person.getFirstName()) && medicalRecord.getLastName().equals(person.getLastName())) {
                        dtoFloodPerson.setAge(medicalRecord.getAgeFromMedicalRecords(medicalRecords, person.getFirstName(), person.getLastName()));
                        dtoFloodPerson.setMedications(medicalRecord.getMedications());
                        dtoFloodPerson.setAllergies(medicalRecord.getAllergies());
                        dtoFloodPersonList.add(dtoFloodPerson);
                    }
                }
            }

            dtoFlood.setAddress(firestation.getAddress());
            dtoFlood.setFloodAlertPersonsDTO(dtoFloodPersonList);
            dtoFloodList.add(dtoFlood);
            persons.clear();
        }
        return dtoFloodList;
    }

}
