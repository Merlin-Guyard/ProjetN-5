package com.mg.warning.alert;


import com.mg.warning.alert.emailAlert.EmailAlertDTO;
import com.mg.warning.alert.emailAlert.EmailAlertService;
import com.mg.warning.alert.personInfoAlert.PersonInfoAlertDTO;
import com.mg.warning.alert.personInfoAlert.PersonInfoAlertService;
import com.mg.warning.alert.phoneAlert.PhoneAlertDTO;
import com.mg.warning.alert.phoneAlert.PhoneAlertService;
import com.mg.warning.alert.childrenAlert.ChildAlertService;
import com.mg.warning.alert.childrenAlert.ChildrenAlertWithFamilyDTO;
import com.mg.warning.alert.firestationAlert.FireStationAlertService;
import com.mg.warning.alert.firestationAlert.FirestationAlertWithNbDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class AlertController {

    @Autowired
    private FireStationAlertService fireStationAlertService;

    @Autowired
    private ChildAlertService childAlertService;

    @Autowired
    private PersonInfoAlertService personInfoAlertService;

    @Autowired
    private PhoneAlertService phoneService;

    @Autowired
    private EmailAlertService emailService;

    @GetMapping(value = "/firestation")
    public FirestationAlertWithNbDTO getAllFirestation(@RequestParam("stationNumber") int stationNumber) {
        return fireStationAlertService.getFirestationAlertDTOWithSum(stationNumber);
    }

    @GetMapping(value = "/childAlert")
    public ChildrenAlertWithFamilyDTO getAllChildrenAndFamily(@RequestParam("address") String address) {
        return childAlertService.getChildrenWithFamilyDTO(address);
    }

    @GetMapping(value = "/phoneAlert")
    public List<PhoneAlertDTO> getAllPhone(@RequestParam("firestation") int stationNumber) {
        return phoneService.getPhoneDTO(stationNumber);
    }

//    @GetMapping(value = "/fire")
//    public List<PhoneAlertDTO> getAllFire(@RequestParam("firestation") int stationNumber) {
//        return phoneService.getPhoneDTO(stationNumber);
//    }
//
//    @GetMapping(value = "/flood/stations")
//    public List<PhoneAlertDTO> getAllFlood(@RequestParam("firestation") int stationNumber) {
//        return phoneService.getPhoneDTO(stationNumber);
//    }

    @GetMapping(value = "/personInfo")
    public List<PersonInfoAlertDTO> getAllPersonInfo(@RequestParam("firstName") String firstname, @RequestParam("lastName") String lastname) {
        return personInfoAlertService.getPersonInfoDTO(firstname, lastname);
    }

    @GetMapping(value = "/communityEmail")
    public List<EmailAlertDTO> getAllEmail(@RequestParam("city") String city) {
        return emailService.getEmailDTO(city);
    }
}
