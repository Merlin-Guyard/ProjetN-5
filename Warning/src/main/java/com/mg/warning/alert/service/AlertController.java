package com.mg.warning.alert.service;


import com.mg.warning.alert.phone.PhoneDTO;
import com.mg.warning.alert.phone.PhoneService;
import com.mg.warning.alert.children.ChildService;
import com.mg.warning.alert.email.EmailDTO;
import com.mg.warning.alert.email.EmailService;
import com.mg.warning.alert.fire.FireDTO;
import com.mg.warning.alert.fire.FireService;
import com.mg.warning.alert.firestation.FireStationService;
import com.mg.warning.alert.flood.FloodDTO;
import com.mg.warning.alert.flood.FloodService;
import com.mg.warning.alert.personInfo.PersonInfoDTO;
import com.mg.warning.alert.personInfo.PersonInfoService;
import com.mg.warning.alert.children.ChildrenWithFamilyDTO;
import com.mg.warning.alert.firestation.FirestationWithNbDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tinylog.Logger;

import java.util.Arrays;
import java.util.List;


@RestController
public class AlertController {

    @Autowired
    private FireStationService fireStationService;

    @Autowired
    private ChildService childService;

    @Autowired
    private PhoneService phoneService;

    @Autowired
    private FireService fireService;

    @Autowired
    private FloodService floodService;

    @Autowired
    private PersonInfoService personInfoService;

    @Autowired
    private EmailService emailService;

    @GetMapping(value = "/firestation")
    public FirestationWithNbDTO getAllFirestation(@RequestParam("stationNumber") int stationNumber) {
        Logger.info("/firestation?stationNumber={} firestation alert called", stationNumber);
        return fireStationService.getFirestationAlertDTOWithSum(stationNumber);
    }

    @GetMapping(value = "/childAlert")
    public ChildrenWithFamilyDTO getAllChildrenAndFamily(@RequestParam("address") String address) {
        Logger.info("/childAlert?address={} child alert called", address);
        return childService.getChildrenWithFamilyDTO(address);
    }

    @GetMapping(value = "/phoneAlert")
    public List<PhoneDTO> getAllPhone(@RequestParam("firestation") int stationNumber) {
        Logger.info("/phoneAlert?firestation={} phone alert called", stationNumber);
        return phoneService.getPhoneDTO(stationNumber);
    }

    @GetMapping(value = "/fire")
    public FireDTO getAllFire(@RequestParam("address") String address) {
        Logger.info("/fire?address={} fire alert called", address);
        return fireService.getFireDTO(address);
    }

    @GetMapping(value = "/flood/stations")
    public List<FloodDTO> getAllFlood(@RequestParam("stations") int[] stationNumber) {
        Logger.info("/flood/stations?stations={} flood alert called", Arrays.toString(stationNumber));
        return floodService.getFloodDTO(stationNumber);
    }

    @GetMapping(value = "/personInfo")
    public List<PersonInfoDTO> getAllPersonInfo(@RequestParam("firstName") String firstname, @RequestParam("lastName") String lastname) {
        Logger.info("/personInfo?firstName={}&lastName={} person info alert called", firstname, lastname);
        return personInfoService.getPersonInfoDTO(firstname, lastname);
    }

    @GetMapping(value = "/communityEmail")
    public List<EmailDTO> getAllEmail(@RequestParam("city") String city) {
        Logger.info("/communityEmail?city={} child alert called", city);
        return emailService.getEmailDTO(city);
    }
}
