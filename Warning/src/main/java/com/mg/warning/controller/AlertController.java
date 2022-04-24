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
        FirestationWithNbDTO firestationWithNbDTO = fireStationService.getFirestationAlertDTOWithSum(stationNumber);
        if (firestationWithNbDTO.getFirestationAlertDTOS().isEmpty()){
            Logger.error("la station n°{} n'existe pas", stationNumber);
        }
        return firestationWithNbDTO;
    }

    @GetMapping(value = "/childAlert")
    public ChildrenWithFamilyDTO getAllChildrenAndFamily(@RequestParam("address") String address) {
        Logger.info("/childAlert?address={} child alert called", address);
        ChildrenWithFamilyDTO childrenWithFamilyDTO = childService.getChildrenWithFamilyDTO(address);
        if (childrenWithFamilyDTO.getChildren().isEmpty() && childrenWithFamilyDTO.getFamily().isEmpty()){
            Logger.error("l'adresse {} n'existe pas ou ne comporte pas d'enfants", address);
        }
        return childrenWithFamilyDTO;
    }

    @GetMapping(value = "/phoneAlert")
    public List<PhoneDTO> getAllPhone(@RequestParam("firestation") int stationNumber) {
        Logger.info("/phoneAlert?firestation={} phone alert called", stationNumber);
        List<PhoneDTO> phoneDTO = phoneService.getPhoneDTO(stationNumber);
        if (phoneDTO.isEmpty()){
            Logger.error("la station n°{} n'existe pas", stationNumber);
        }
        return phoneDTO;
    }

    @GetMapping(value = "/fire")
    public FireDTO getAllFire(@RequestParam("address") String address) {
        Logger.info("/fire?address={} fire alert called", address);
        FireDTO fireDTO = fireService.getFireDTO(address);
        if (fireDTO.getFireAlertPersonsDTO().isEmpty() && fireDTO.getFireAlertStationDTO().isEmpty()){
            Logger.error("l'adresse {} n'existe pas", address);
        }
        return fireDTO;
    }

    @GetMapping(value = "/flood/stations")
    public List<FloodDTO> getAllFlood(@RequestParam("stations") int[] stationNumber) {
        Logger.info("/flood/stations?stations={} flood alert called", Arrays.toString(stationNumber));
        List<FloodDTO> floodDTOS = floodService.getFloodDTO(stationNumber);
        if (floodDTOS.isEmpty()){
            Logger.error("la station n°{} n'existe pas", stationNumber);
        }
        return floodDTOS;
    }

    @GetMapping(value = "/personInfo")
    public List<PersonInfoDTO> getAllPersonInfo(@RequestParam("firstName") String firstname, @RequestParam("lastName") String lastname) {
        Logger.info("/personInfo?firstName={}&lastName={} person info alert called", firstname, lastname);
        List<PersonInfoDTO> personInfoDTOS = personInfoService.getPersonInfoDTO(firstname, lastname);
        if (personInfoDTOS.isEmpty()){
            Logger.error("{} {} n'existe pas", firstname, lastname);
        }
        return personInfoDTOS;
    }

    @GetMapping(value = "/communityEmail")
    public List<EmailDTO> getAllEmail(@RequestParam("city") String city) {
        Logger.info("/communityEmail?city={} child alert called", city);
        List<EmailDTO> emailDTOS = emailService.getEmailDTO(city);
        if (emailDTOS.isEmpty()){
            Logger.error("la ville {} n'existe pas", city);
        }
        return emailDTOS;
    }
}
