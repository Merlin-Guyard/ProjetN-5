package com.mg.warning.alert;

import com.mg.warning.alert.firestation.FirestationAlertDTO;
import com.mg.warning.alert.firestation.FirestationAlertDTOWithSum;
import com.mg.warning.firestation.Firestation;
import com.mg.warning.firestation.FirestationRepository;
import com.mg.warning.medicalRecord.MedicalRecord;
import com.mg.warning.medicalRecord.MedicalRecordRepository;
import com.mg.warning.person.Person;
import com.mg.warning.person.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
public class AlertController {

    @Autowired
    private FirestationRepository firestationRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;


    @GetMapping(value= "/firestation")
    public FirestationAlertDTOWithSum getAllFirestation(@RequestParam("stationNumber") int stationNumber){


        //question comment organiser
        List<Firestation> fireStations = firestationRepository.findById(stationNumber);
        List<Person> persons = new ArrayList<>();
        FirestationAlertDTO dto = new FirestationAlertDTO();
        List<FirestationAlertDTO> dtoList  = new ArrayList<>();
        FirestationAlertDTOWithSum dtoWithSum  = new FirestationAlertDTOWithSum();
        List<MedicalRecord> medicalRecords = new ArrayList<>();
        int adult = 0;
        int children = 0;

        //Get all persons from firestation's address
        for(Firestation firestation: fireStations)  {
            persons.addAll(personRepository.findByAddress(firestation.getAddress()));
        }

        //Get only interested data
        for (Person person : persons){
            dto.setFirstname(person.getFirstName());
            dto.setLastname(person.getLastName());
            dto.setAddress(person.getAddress());
            dto.setPhone(person.getPhone());
            dtoList.add(dto);
        }
        dtoWithSum.setFirestationAlertDTOS(dtoList);


        //Get all medical records from persons
        for(Person person: persons)  {
            medicalRecords.addAll(medicalRecordRepository.findByName(person.getFirstName(), person.getLastName()));
        }

        //Check if minor or adult
        LocalDate dateFrom18YearsAgo = LocalDate.now().minusYears(18);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate personAge;
        for(MedicalRecord medicalRecord: medicalRecords)  {
            personAge = LocalDate.parse(medicalRecord.getBirthdate(), formatter);
            if(personAge.isBefore(dateFrom18YearsAgo)){
                adult++;
            }else{
                children++;
            }
        }
        //ceci est un test


        dtoWithSum.setNbAdults(adult);
        dtoWithSum.setNbChildrens(children);
        return dtoWithSum;
    }
}
