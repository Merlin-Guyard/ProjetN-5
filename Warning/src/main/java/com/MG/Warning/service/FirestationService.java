package com.MG.Warning.service;

import com.MG.Warning.dao.FirestationRepository;
import com.MG.Warning.model.Firestation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FirestationService {

    @Autowired
    private FirestationRepository firestationRepository;

    public List<Firestation> findAll() {
        return firestationRepository.findAll();
    }

}
