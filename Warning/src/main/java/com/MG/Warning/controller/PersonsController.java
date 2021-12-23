package com.MG.Warning.controller;

import com.MG.Warning.dao.PersonRepository;
import com.MG.Warning.dao.PersonsDao;
import com.MG.Warning.model.Persons;
import com.MG.Warning.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonsController {

    @Autowired
    private PersonsDao personsDao;

    @Autowired
    private PersonRepository personRepository;


    @Autowired
    private PersonService personService;


    @GetMapping(value= "/")
    public List<Persons> getAllPersons(){
        return personRepository.findAll();
        //return personsDao.getPersons();
    }

    @GetMapping(value = "/{firstname}")
    public Persons getOnePerson(@PathVariable String firstname, String lastname){
        return personsDao.getPerson(firstname);
    }

    @PostMapping(value = "/")
    public void addOnePerson(@RequestBody Persons persons) {
        personsDao.createPerson(persons);
    }

    @PostMapping(value = "/person/{firstname}")
    public void delOnePerson(@PathVariable String firstname) {
        personsDao.deletePerson(firstname);
    }
}