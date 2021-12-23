package com.MG.Warning.controller;

import com.MG.Warning.dao.PersonsDao;
import com.MG.Warning.model.Persons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PersonsController {

    @Autowired
    private PersonsDao personsDao;


    @GetMapping(value= "person")
    public Iterable<Persons> getAllPersons(){
        return personsDao.getPersons();
    }

    @GetMapping(value = "person/{firstname}")
    public Persons getOnePerson(@PathVariable String firstname, String lastname){
        return personsDao.getPerson(firstname);
    }

    @PostMapping(value = "/person")
    public void addOnePerson(@RequestBody Persons persons) {
        personsDao.createPerson(persons);
    }

    @PostMapping(value = "/person/{firstname}")
    public void delOnePerson(@PathVariable String firstname) {
        personsDao.deletePerson(firstname);
    }
}