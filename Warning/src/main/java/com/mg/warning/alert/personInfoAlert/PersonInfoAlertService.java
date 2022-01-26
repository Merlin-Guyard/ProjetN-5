package com.mg.warning.alert.personInfoAlert;

import com.mg.warning.alert.AlertService;
import com.mg.warning.medicalRecord.MedicalRecord;
import com.mg.warning.medicalRecord.MedicalRecordRepository;
import com.mg.warning.person.Person;
import com.mg.warning.person.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonInfoAlertService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @Autowired
    private AlertService alertService;

    public List<PersonInfoAlertDTO> getPersonInfoDTO(String firstname, String lastname) {
        List<Person> persons = new ArrayList<>(personRepository.findByFirstAndLastName(firstname, lastname));
        List<MedicalRecord> medicalRecords = new ArrayList<>(medicalRecordRepository.findByFirstAndLastName(firstname, lastname));
        List<PersonInfoAlertDTO> dtoPersonInfoList = new ArrayList<>();

        //Get only interested data
        for (Person person : persons) {
            PersonInfoAlertDTO dtoPersonInfo = new PersonInfoAlertDTO();

            dtoPersonInfo.setLastName(person.getLastName());
            dtoPersonInfo.setAddress(person.getAddress());
            dtoPersonInfo.setEmail(person.getEmail());
            for (MedicalRecord medicalRecord : medicalRecords){
                if(medicalRecord.getFirstName().equals(person.getFirstName())&&medicalRecord.getLastName().equals(person.getLastName())){
                    dtoPersonInfo.setAge(alertService.getAgeFromMedicalRecords(medicalRecords, person.getFirstName(), person.getLastName()));
                    dtoPersonInfo.setMedications(medicalRecord.getMedications());
                    dtoPersonInfo.setAllergies(medicalRecord.getAllergies());
                }
                dtoPersonInfoList.add(dtoPersonInfo);
            }
        }

        return dtoPersonInfoList;
    }
}
