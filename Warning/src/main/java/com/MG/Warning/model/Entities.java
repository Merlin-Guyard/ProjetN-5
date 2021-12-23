package com.MG.Warning.model;

import java.util.ArrayList;
import java.util.List;

public class Entities {

    private List<Persons> persons = new ArrayList<>();
    private List<Firestations> firestations = new ArrayList<>();
    private List<MedicalRecords> medicalrecords = new ArrayList<>();

    public List<Persons> getPersons() {
        return persons;
    }

    public void setPersons(List<Persons> persons) {
        this.persons = persons;
    }

    public List<Firestations> getFirestations() {
        return firestations;
    }

    public void setFirestations(List<Firestations> firestations) {
        this.firestations = firestations;
    }

    public List<MedicalRecords> getMedicalrecords() {
        return medicalrecords;
    }

    public void setMedicalrecords(List<MedicalRecords> medicalrecords) {
        this.medicalrecords = medicalrecords;
    }
}
