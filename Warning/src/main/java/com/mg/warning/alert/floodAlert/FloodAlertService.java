package com.mg.warning.alert.floodAlert;

import com.mg.warning.alert.AlertService;
import com.mg.warning.firestation.Firestation;
import com.mg.warning.firestation.FirestationRepository;
import com.mg.warning.medicalRecord.MedicalRecord;
import com.mg.warning.medicalRecord.MedicalRecordRepository;
import com.mg.warning.person.Person;
import com.mg.warning.person.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FloodAlertService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private FirestationRepository firestationRepository;

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @Autowired
    private AlertService alertService;

    public List<FloodAlertDTO> getFlood(int[] stationNumber) {

        List<Firestation> fireStations = new ArrayList<>();
        List<MedicalRecord> medicalRecords = medicalRecordRepository.findAll();
        List<Person> persons = new ArrayList<>();
        List<FloodAlertDTO> dtoFloodList = new ArrayList<>();

        for (int i : stationNumber) {
            fireStations.addAll(firestationRepository.findByStationNumber(i));
        }
        
        for (Firestation firestation : fireStations) {
            FloodAlertDTO dtoFlood = new FloodAlertDTO();
            List<FloodAlertPersonsDTO> dtoFloodPersonList = new ArrayList<>();
            persons.addAll(personRepository.findByAddress(firestation.getAddress()));

            for (Person person : persons) {
                FloodAlertPersonsDTO dtoFloodPerson = new FloodAlertPersonsDTO();
                dtoFloodPerson.setLastName(person.getLastName());
                dtoFloodPerson.setPhone(person.getPhone());
                for (MedicalRecord medicalRecord : medicalRecords) {
                    if (medicalRecord.getFirstName().equals(person.getFirstName()) && medicalRecord.getLastName().equals(person.getLastName())) {
                        dtoFloodPerson.setAge(alertService.getAgeFromMedicalRecords(medicalRecords, person.getFirstName(), person.getLastName()));
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
