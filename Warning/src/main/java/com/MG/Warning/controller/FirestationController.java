package com.mg.warning.controller;

import com.mg.warning.dao.FirestationRepository;
import com.mg.warning.model.Firestation;
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
