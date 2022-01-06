package com.mg.warning.controller;

import com.mg.warning.dao.PersonRepository;
import com.mg.warning.model.Person;
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
        personRepository.save(person);
    }

    @GetMapping(value= "/Put/{firstname}+{lastname}")
    public void updateOnePerson(@RequestBody Person person){
        personRepository.update(person);
    }

    @GetMapping(value= "/Delete/{firstname}+{lastname}")
    public void delOnePerson(@PathVariable("firstname") String firstname, @PathVariable("lastname") String lastname){
        personRepository.delete(firstname, lastname);
    }
}