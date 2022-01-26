package com.mg.warning.firestation;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FirestationRepository {

    private List<Firestation> firestationList = new ArrayList<>();

    //GET
    public List<Firestation> findAll() {
        return firestationList;
    }

    //PUT
    public void update(Firestation firestation) {
        int index = 0;
        List<Firestation> copy = new ArrayList<>(firestationList);
        for (Firestation firestationLoop : copy) {
            if(firestationLoop.equals(firestation)) {
                firestationList.remove(firestationLoop);
                firestationList.add(firestation);
            }
        }
    }

    //POST
    public void save(Firestation firestation) {
        firestationList.add(firestation);
    }

    //DELETE
    public void delete(String address, int station) {
        firestationList.removeIf(firestation -> firestation.getAddress().equals(address) && firestation.getStation() == station);
    }

    //GET BY ID
    public List<Firestation> findByStationNumber(int stationNumber) {
        List<Firestation> result = new ArrayList<>();
        for(Firestation firestation: firestationList)  {
            if(firestation.getStation() == stationNumber) {
                result.add(firestation);
            }
        }
        return result;
    }
}