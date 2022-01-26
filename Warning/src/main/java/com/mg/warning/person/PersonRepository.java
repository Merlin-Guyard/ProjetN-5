package com.mg.warning.person;


import com.mg.warning.person.Person;
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

    //GET BY FIRSTNAME & LASTNAME
    public List<Person> findByFirstAndLastName(String firstname, String lastname) {
        List<Person> result = new ArrayList<>();
        for (Person person : personsList) {
            if(person.getFirstName().equals(firstname) && person.getLastName().equals(lastname)) {
                result.add(person);
            }
        }
        return result;
    }

    //PUT
    public void update(Person person) {
        int index = 0;
        List<Person> copy = new ArrayList<>(personsList);
        for (Person personLoop : copy) {
            /*if (p.getFirstName().equals(person.getFirstName()) && p.getLastName().equals(person.getLastName())) {
                personsList.set(index, person);
                index++;
            }*/
            if(personLoop.equals(person)) {
                personsList.remove(personLoop);
                personsList.add(person);
            }
        }
    }

    //PUT V2
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
        personsList.removeIf(person -> person.getFirstName().equals(firstname) && person.getLastName().equals(lastname));
    }

    //GET BY ADDRESS
    public List<Person> findByAddress(String address) {
        List<Person> result = new ArrayList<>();
        for (Person person : personsList) {
            if(person.getAddress().equals(address)) {
                result.add(person);
            }
        }
        return result;
    }

    //GET BY City
    public List<Person> findByCity(String city) {
        List<Person> result = new ArrayList<>();
        for (Person person : personsList) {
            if(person.getCity().equals(city)) {
                result.add(person);
            }
        }
        return result;
    }
}
