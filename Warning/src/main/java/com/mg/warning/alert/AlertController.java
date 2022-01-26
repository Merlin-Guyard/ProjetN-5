package com.mg.warning.alert;


import com.mg.warning.alert.children.ChildAlertFindChildrenAndFamilyService;
import com.mg.warning.alert.children.ChildrenWithFamilyDTO;
import com.mg.warning.alert.firestation.FireStationAlertFindPersonsAndNbService;
import com.mg.warning.alert.firestation.FirestationAlertWithNbDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AlertController {

    @Autowired
    private FireStationAlertFindPersonsAndNbService fireStationAlertFindPersonsAndNbService;

    @Autowired
    private ChildAlertFindChildrenAndFamilyService childAlertFindChildrensAndFamilyService;

    @GetMapping(value = "/firestation")
    public FirestationAlertWithNbDTO getAllFirestation(@RequestParam("stationNumber") int stationNumber) {
        return fireStationAlertFindPersonsAndNbService.getFirestationAlertDTOWithSum(stationNumber);
    }

    @GetMapping(value = "/childAlert")
    public ChildrenWithFamilyDTO getAllChildrenAndFamily(@RequestParam("address") String address) {
        return childAlertFindChildrensAndFamilyService.getChildrenWithFamilyDTO(address);
    }
}
