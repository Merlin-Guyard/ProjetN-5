package com.MG.Warning.controller;

import com.MG.Warning.dao.PersonRepository;
import com.MG.Warning.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping(value= "/GetAll")
    public List<Person> getAllPersons(){
        return personRepository.findAll();
    }

    @GetMapping(value= "/Post")
    public void postOnePerson(@RequestBody Person person){
        personRepository.createPerson(person);
    }

    @GetMapping(value= "/Put")
    public void updateOnePerson(@PathVariable("firstname") String firstname, @PathVariable("lastname") String lastname, @RequestBody Person person){
        personRepository.updatePerson(firstname,lastname, person);
    }

    @GetMapping(value= "/Delete/{firstname}+{lastname}")
    public void delOnePerson(@PathVariable("firstname") String firstname, @PathVariable("lastname") String lastname){
        personRepository.deletePerson(firstname, lastname);
    }
}