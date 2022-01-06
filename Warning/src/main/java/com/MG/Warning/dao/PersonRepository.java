package com.mg.warning.dao;


import com.mg.warning.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonRepository {

    private List<Person> personsList = new ArrayList<>();

    //GET
    public List<Person> findAll() {
        return personsList;
    }

    //PUT
    public void update(Person person) {
        int index = 0;
        for (Person p : personsList) {
            if (p.getFirstName().equals(person.getFirstName()) && p.getLastName().equals(person.getLastName())) {
                personsList.set(index, person);
                index++;
            }
        }
    }

    //POST
    public void save(Person persons) {
        personsList.add(persons);
    }

    //DELETE
    public void delete(String firstname, String lastname) {
        personsList.removeIf(p -> p.getFirstName().equals(firstname) && p.getLastName().equals(lastname));
    }
}
