package com.mg.warning.alert;


import com.mg.warning.alert.children.ChildrenWithFamilyDTO;
import com.mg.warning.alert.children.FamilyDTO;
import com.mg.warning.alert.firestation.FireStationAlertFindPersonsAndNbService;
import com.mg.warning.alert.firestation.FirestationAlertWithNbDTO;
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
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@RestController
public class AlertController {

    @Autowired
    private FireStationAlertFindPersonsAndNbService fireStationAlertFindPersonsAndNbService;

    @GetMapping(value = "/firestation")
    public FirestationAlertWithNbDTO getAllFirestation(@RequestParam("stationNumber") int stationNumber) {
        return fireStationAlertFindPersonsAndNbService.getFirestationAlertDTOWithSum(stationNumber);
    }

    @GetMapping(value = "/childAlert")
    public List<ChildrenWithFamilyDTO> getAllChildrenAndFamily(@RequestParam("address") String address) {
//        FireStationAlertFindPersonsAndNbService fireStationAlertFindPersonsAndNbService = new FireStationAlertFindPersonsAndNbService();
//        return fireStationAlertFindPersonsAndNbService.getFirestationAlertDTOWithSum(stationNumber);
//        String childAddress = null;
//        List<MedicalRecord> medicalRecords = medicalRecordRepository.findAll();
//        List<Person> persons = personRepository.findAll();
//        ChildrenWithFamilyDTO dtoChild = new ChildrenWithFamilyDTO();
//        List<ChildrenWithFamilyDTO> dtoChildList = new ArrayList<>();
//        FamilyDTO dtoFamily = new FamilyDTO();
//
//
//        LocalDate dateFrom18YearsAgo = LocalDate.now().minusYears(18);
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
//        LocalDate personAge;
//        for (MedicalRecord medicalRecord : medicalRecords) {
//            personAge = LocalDate.parse(medicalRecord.getBirthdate(), formatter);
//            if (personAge.isAfter(dateFrom18YearsAgo)) {
//                dtoChild.setFirstname(medicalRecord.getFirstName());
//                dtoChild.setLastname(medicalRecord.getLastName());
//
//
//                for (Person person1 : persons) {
//                    if (dtoChild.getFirstname().equals(person1.getFirstName()) && dtoChild.getLastname().equals(person1.getLastName())) {
//                        childAddress = person1.getAddress();
//                    }
//
//                    List<FamilyDTO> dtoWithFamilyList = new ArrayList<>();
//                    for (Person person2 : persons) {
//                        if (person2.getAddress().equals(childAddress)) {
//                            dtoFamily.setFirstname(person2.getFirstName());
//                            dtoFamily.setLastname(person2.getLastName());
//                            dtoWithFamilyList.add(dtoFamily);
//                        }
//
//
//                        dtoChild.setAge(Period.between(personAge, LocalDate.now()).getYears());
//                        dtoChild.setFamily(dtoWithFamilyList);
//                        dtoChildList.add(dtoChild);
//                    }
//                }
//
//
//            }
//
//        }
        return null;
    }
}
