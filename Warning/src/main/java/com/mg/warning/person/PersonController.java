package com.mg.warning.person;

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

    @PostMapping(value= "")
    public void postOnePerson(@RequestBody Person person){
        personRepository.save(person);
    }

    @PutMapping(value= "")
    public void updateOnePerson(@RequestBody Person person){
        personRepository.update(person);
    }

    @DeleteMapping(value= "")
    public void delOnePerson(@PathVariable("firstname") String firstname, @PathVariable("lastname") String lastname){
        personRepository.delete(firstname, lastname);
    }
}