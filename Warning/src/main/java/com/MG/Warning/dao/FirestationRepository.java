package com.mg.warning.dao;


import com.mg.warning.model.Firestation;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FirestationRepository {

    private List<Firestation> firestationList = new ArrayList<>();

    public void save(Firestation firestation) {
        firestationList.add(firestation);
    }

    public List<Firestation> findAll() {
        return firestationList;
    }
}
