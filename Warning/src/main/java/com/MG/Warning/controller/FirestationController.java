package com.MG.Warning.controller;

import com.MG.Warning.dao.FirestationRepository;
import com.MG.Warning.model.Firestation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/firestation")
public class FirestationController {

    @Autowired
    private FirestationRepository firestationRepository;

    @GetMapping(value= "/GetAll")
    public List<Firestation> getAllFirestation(){
        return firestationRepository.findAll();
    }
}
