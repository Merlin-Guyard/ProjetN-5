package com.MG.Warning.dao;


import com.MG.Warning.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonRepository {

    // CRUD

    private List<Person> personsList = new ArrayList<>();

    public void save(Person persons) {
        personsList.add(persons);
    }

    public List<Person> findAll() {
        return personsList;
    }

    public void createPerson(Person person) {

    }

    public void updatePerson(String firstname, String lastname, Person person) {

    }

    public void deletePerson(String firstname, String lastname) {

    }
}
