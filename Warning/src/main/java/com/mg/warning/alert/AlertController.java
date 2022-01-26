package com.mg.warning.alert;


import com.mg.warning.alert.phoneAlert.PhoneAlertDTO;
import com.mg.warning.alert.phoneAlert.PhoneService;
import com.mg.warning.alert.children.ChildAlertFindChildrenAndFamilyService;
import com.mg.warning.alert.children.ChildrenWithFamilyAlertDTO;
import com.mg.warning.alert.firestation.FireStationAlertFindPersonsAndNbService;
import com.mg.warning.alert.firestation.FirestationAlertWithNbDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class AlertController {

    @Autowired
    private FireStationAlertFindPersonsAndNbService fireStationAlertFindPersonsAndNbService;

    @Autowired
    private ChildAlertFindChildrenAndFamilyService childAlertFindChildrenAndFamilyService;

    @Autowired
    private PhoneService phoneService;

    @GetMapping(value = "/firestation")
    public FirestationAlertWithNbDTO getAllFirestation(@RequestParam("stationNumber") int stationNumber) {
        return fireStationAlertFindPersonsAndNbService.getFirestationAlertDTOWithSum(stationNumber);
    }

    @GetMapping(value = "/childAlert")
    public ChildrenWithFamilyAlertDTO getAllChildrenAndFamily(@RequestParam("address") String address) {
        return childAlertFindChildrenAndFamilyService.getChildrenWithFamilyDTO(address);
    }

    @GetMapping(value = "/phoneAlert")
    public List<PhoneAlertDTO> getAllPhone(@RequestParam("firestation") int stationNumber) {
        return phoneService.getPhoneDTO(stationNumber);
    }
}
