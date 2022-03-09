package com.mg.warning.alert;


import com.mg.warning.alert.PhoneAlert.PhoneAlertDTO;
import com.mg.warning.alert.PhoneAlert.PhoneAlertService;
import com.mg.warning.alert.email.EmailDTO;
import com.mg.warning.alert.email.EmailService;
import com.mg.warning.alert.fire.FireDTO;
import com.mg.warning.alert.fire.FireService;
import com.mg.warning.alert.floodAlert.FloodAlertDTO;
import com.mg.warning.alert.floodAlert.FloodAlertService;
import com.mg.warning.alert.personInfoAlert.PersonInfoAlertDTO;
import com.mg.warning.alert.personInfoAlert.PersonInfoAlertService;
import com.mg.warning.alert.childrenAlert.ChildAlertService;
import com.mg.warning.alert.childrenAlert.ChildrenAlertWithFamilyDTO;
import com.mg.warning.alert.firestationAlert.FireStationAlertService;
import com.mg.warning.alert.firestationAlert.FirestationAlertWithNbDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;


@RestController
public class AlertController {

    Logger logger = LoggerFactory.getLogger(AlertController.class);

    @Autowired
    private FireStationAlertService fireStationAlertService;

    @Autowired
    private ChildAlertService childAlertService;

    @Autowired
    private PhoneAlertService phoneAlertService;

    @Autowired
    private FireService fireAlertService;

    @Autowired
    private FloodAlertService floodAlertService;

    @Autowired
    private PersonInfoAlertService personInfoAlertService;

    @Autowired
    private EmailService emailService;

    @GetMapping(value = "/firestation")
    public FirestationAlertWithNbDTO getAllFirestation(@RequestParam("stationNumber") int stationNumber) {
        logger.info("/firestation?stationNumber={} firestation alert called", stationNumber);
        return fireStationAlertService.getFirestationAlertDTOWithSum(stationNumber);
    }

    @GetMapping(value = "/childAlert")
    public ChildrenAlertWithFamilyDTO getAllChildrenAndFamily(@RequestParam("address") String address) {
        logger.info("/childAlert?address={} child alert called", address);
        return childAlertService.getChildrenWithFamilyDTO(address);
    }

    @GetMapping(value = "/phoneAlert")
    public List<PhoneAlertDTO> getAllPhone(@RequestParam("firestation") int stationNumber) {
        logger.info("/phoneAlert?firestation={} phone alert called", stationNumber);
        return phoneAlertService.getPhoneDTO(stationNumber);
    }

    @GetMapping(value = "/fire")
    public FireDTO getAllFire(@RequestParam("address") String address) {
        logger.info("/fire?address={} fire alert called", address);
        return fireAlertService.getFireDTO(address);
    }

    @GetMapping(value = "/flood/stations")
    public List<FloodAlertDTO> getAllFlood(@RequestParam("stations") int[] stationNumber) {
        logger.info("/flood/stations?stations={} flood alert called", Arrays.toString(stationNumber));
        return floodAlertService.getFloodDTO(stationNumber);
    }

    @GetMapping(value = "/personInfo")
    public List<PersonInfoAlertDTO> getAllPersonInfo(@RequestParam("firstName") String firstname, @RequestParam("lastName") String lastname) {
        logger.info("/personInfo?firstName={}&lastName={} person info alert called", firstname, lastname);
        return personInfoAlertService.getPersonInfoDTO(firstname, lastname);
    }

    @GetMapping(value = "/communityEmail")
    public List<EmailDTO> getAllEmail(@RequestParam("city") String city) {
        logger.info("/communityEmail?city={} child alert called", city);
        return emailService.getEmailDTO(city);
    }
}
