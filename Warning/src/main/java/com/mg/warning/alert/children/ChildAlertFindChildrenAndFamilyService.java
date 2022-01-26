package com.mg.warning.alert.children;


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
public class ChildAlertFindChildrenAndFamilyService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @Autowired
    private AlertService alertService;

    public ChildrenWithFamilyDTO getChildrenWithFamilyDTO(String address) {

        List<MedicalRecord> medicalRecords = medicalRecordRepository.findAll();
        List<Person> persons = personRepository.findAll();
        ChildrenWithFamilyDTO dtoChildrenAndFamily = new ChildrenWithFamilyDTO();
        ChildrenDTO dtoChildren = new ChildrenDTO();
        List<ChildrenDTO> dtoChildrenList = new ArrayList<>();
        FamilyDTO dtoFamily = new FamilyDTO();
        List<FamilyDTO> dtoFamilyList = new ArrayList<>();
        int personAge;

        //Check by address then age
        for (Person person : persons) {
            if (person.getAddress().equals(address)) {
                    personAge = alertService.getAgeFromMedicalRecords(medicalRecords, person.getFirstName(), person.getLastName());

                    if (personAge >= 18) {
                        dtoFamily.setFirstname(person.getFirstName());
                        dtoFamily.setLastname(person.getLastName());
                        dtoFamilyList.add(dtoFamily);
                    } else {
                        dtoChildren.setFirstname(person.getFirstName());
                        dtoChildren.setLastname(person.getLastName());
                        dtoChildren.setAge(personAge);
                        dtoChildrenList.add(dtoChildren);

                }
            }
        }

        //Check if a children were found otherwise return a blank list
        if (dtoChildrenList.size() > 0) {
            dtoChildrenAndFamily.setFamily(dtoFamilyList);
            dtoChildrenAndFamily.setChildren(dtoChildrenList);
        }

        return dtoChildrenAndFamily;
    }
}
