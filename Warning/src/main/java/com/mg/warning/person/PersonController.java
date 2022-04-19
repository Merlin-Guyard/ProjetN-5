package com.mg.warning.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tinylog.Logger;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping(value = "/GetAll")
    public List<Person> getAllPersons() {
        Logger.info("/person function get all called");
        return personRepository.findAll();
    }

    @PostMapping(value = "")
    public void postOnePerson(@RequestBody Person person) {
        Logger.info("/person function post called for {}, {}", person.getFirstName(), person.getLastName());
        personRepository.save(person);
    }

    @PutMapping(value = "")
    public void updateOnePerson(@RequestBody Person person) {
        Logger.info("/person function update called for {}, {}", person.getFirstName(), person.getLastName());
        personRepository.update(person);
    }

    @DeleteMapping(value = "/{firstname}/{lastname}")
    public void delOnePerson(@PathVariable("firstname") String firstname, @PathVariable("lastname") String lastname) {
        Logger.info("/person function delete called for {}, {}", firstname, lastname);
        personRepository.delete(firstname, lastname);
    }
}