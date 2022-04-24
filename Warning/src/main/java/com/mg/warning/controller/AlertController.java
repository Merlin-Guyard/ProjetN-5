package com.mg.warning.controller;


import com.mg.warning.dto.PhoneDTO;
import com.mg.warning.dto.EmailDTO;
import com.mg.warning.dto.FireDTO;
import com.mg.warning.dto.FloodDTO;
import com.mg.warning.dto.PersonInfoDTO;
import com.mg.warning.dto.ChildrenWithFamilyDTO;
import com.mg.warning.dto.FirestationWithNbDTO;
import com.mg.warning.service.*;
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
    private FirestationAlertService fireStationService;

    @Autowired
    private ChildAlertService childService;

    @Autowired
    private PhoneAlertService phoneService;

    @Autowired
    private FireAlertService fireService;

    @Autowired
    private FloodAlertService floodService;

    @Autowired
    private PersonInfoAlertService personInfoService;

    @Autowired
    private EmailAlertService emailService;

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