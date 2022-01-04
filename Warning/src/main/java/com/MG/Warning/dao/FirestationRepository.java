package com.MG.Warning.dao;


import com.MG.Warning.model.Firestation;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FirestationRepository {

    // CRUD

    private List<Firestation> firestationList = new ArrayList<>();

    public void save(Firestation firestation) {
        firestationList.add(firestation);
    }

    public List<Firestation> findAll() {
        return firestationList;
    }
}
