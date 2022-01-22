package com.mg.warning.alert;


import com.mg.warning.alert.firestation.FireStationAlertFindPersonsAndNbService;
import com.mg.warning.alert.firestation.FirestationAlertDTOWithSum;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AlertController {


    @GetMapping(value= "/firestation")
    public FirestationAlertDTOWithSum getAllFirestation(@RequestParam("stationNumber") int stationNumber){
        FireStationAlertFindPersonsAndNbService fireStationAlertFindPersonsAndNbService = new FireStationAlertFindPersonsAndNbService();
        return fireStationAlertFindPersonsAndNbService.getFirestationAlertDTOWithSum(stationNumber);
    }
}
