package com.mg.warning.alert.firestation;

import com.mg.warning.person.Person;

import java.util.ArrayList;
import java.util.List;

public class FirestationAlertDTO {

    private List<Person> persons = new ArrayList<>();
    private int nbAdults;
    private int nbChildren;

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public int getNbAdults() {
        return nbAdults;
    }

    public void setNbAdults(int nbAdults) {
        this.nbAdults = nbAdults;
    }

    public int getNbChildren() {
        return nbChildren;
    }

    public void setNbChildren(int nbChildren) {
        this.nbChildren = nbChildren;
    }
}
