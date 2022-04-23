package com.mg.warning.service;

import com.mg.warning.model.Firestation;
import com.mg.warning.repository.FirestationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tinylog.Logger;

import java.util.List;

@Service
public class FirestationService {

    @Autowired
    private FirestationRepository firestationRepository;


    public List<Firestation> getAllFirestation() {
        Logger.debug("IT WORKS");
        return firestationRepository.findAll();
    }
}
