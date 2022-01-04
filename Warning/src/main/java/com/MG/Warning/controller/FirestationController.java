package com.MG.Warning.controller;

import com.MG.Warning.dao.FirestationRepository;
import com.MG.Warning.dao.PersonRepository;
import com.MG.Warning.model.Firestation;
import com.MG.Warning.model.Person;
import com.MG.Warning.service.FirestationService;
import com.MG.Warning.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/firestation")
public class FirestationController {

    @Autowired
    private FirestationRepository firestationRepository;

    @Autowired
    private FirestationService firestationService;

    @GetMapping(value= "/GetAll")
    public List<Firestation> getAllFirestation(){
        return firestationRepository.findAll();
    }
}
