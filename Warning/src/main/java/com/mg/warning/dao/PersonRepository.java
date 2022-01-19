package com.mg.warning.dao;


import com.mg.warning.model.Firestation;
import com.mg.warning.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
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
        List<Person> copy = new ArrayList<>(personsList);
        for (Person p : copy) {
            /*if (p.getFirstName().equals(person.getFirstName()) && p.getLastName().equals(person.getLastName())) {
                personsList.set(index, person);
                index++;
            }*/
            if(p.equals(person)) {
                personsList.remove(p);
                personsList.add(person);
            }
        }
    }

    public void update2(Person person) {
        personsList
                .stream()
                .filter(each -> each.equals(person))
                .forEach(each -> {
                    personsList.remove(each);
                    personsList.add(person);
                });
    }

    //POST
    public void save(Person persons) {
        personsList.add(persons);
    }

    //DELETE
    public void delete(String firstname, String lastname) {
        personsList.removeIf(p -> p.getFirstName().equals(firstname) && p.getLastName().equals(lastname));
    }

    public List<Person> findByAddress(String address) {
        List<Person> result = new ArrayList<>();
        for (Person person : personsList) {
            if(person.getAddress().equals(address)) {
                result.add(person);
            }
        }
        return result;
    }
}
