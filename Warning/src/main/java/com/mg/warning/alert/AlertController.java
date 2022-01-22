package com.mg.warning.alert;


import com.mg.warning.alert.children.ChildrenAlertWithFamilyDTO;
import com.mg.warning.alert.firestation.FireStationAlertFindPersonsAndNbService;
import com.mg.warning.alert.firestation.FirestationAlertWithNbDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AlertController {


    @GetMapping(value= "/firestation")
    public FirestationAlertWithNbDTO getAllFirestation(@RequestParam("stationNumber") int stationNumber){
        FireStationAlertFindPersonsAndNbService fireStationAlertFindPersonsAndNbService = new FireStationAlertFindPersonsAndNbService();
        return fireStationAlertFindPersonsAndNbService.getFirestationAlertDTOWithSum(stationNumber);
    }

    @GetMapping(value= "/childAlert")
    public ChildrenAlertWithFamilyDTO getAllChildrenAndFamily(@RequestParam("address") String address){
//        FireStationAlertFindPersonsAndNbService fireStationAlertFindPersonsAndNbService = new FireStationAlertFindPersonsAndNbService();
//        return fireStationAlertFindPersonsAndNbService.getFirestationAlertDTOWithSum(stationNumber);
        return  null;
    }
}
