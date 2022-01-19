package com.mg.warning.alert.firestation;

import java.util.ArrayList;
import java.util.List;

public class FirestationAlertDTOWithSum {



    private List<FirestationAlertDTO> firestationAlertDTOS = new ArrayList<>();
    private int nbAdults;
    private int nbChildren;

    public List<FirestationAlertDTO> getFirestationAlertDTOS() {
        return firestationAlertDTOS;
    }

    public void setFirestationAlertDTOS(List<FirestationAlertDTO> firestationAlertDTOS) {
        this.firestationAlertDTOS = firestationAlertDTOS;
    }

    public int getNbAdults() {
        return nbAdults;
    }

    public void setNbAdults(int nbAdults) {
        this.nbAdults = nbAdults;
    }

    public int getNbChildren() {
        return nbChildren;
    }

    public void setNbChildren(int nbChildren) {
        this.nbChildren = nbChildren;
    }
}
