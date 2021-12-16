package com.MG.Warning.model;

import java.util.Arrays;

public class MedicalRecords {

    private String firstName;
    private String lastName;
    private String birthdate;
    private String[] medication;
    private String[] allergies;

    public MedicalRecords() {
    }

    public MedicalRecords(String[] medication, String[] allergies) {
        this.medication = medication;
        this.allergies = allergies;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String[] getMedication() {
        return medication;
    }

    public void setMedication(String[] medication) {
        this.medication = medication;
    }

    public String[] getAllergies() {
        return allergies;
    }

    public void setAllergies(String[] allergies) {
        this.allergies = allergies;
    }

    @Override
    public String toString() {
        return "MedicalRecords{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", medication=" + Arrays.toString(medication) +
                ", allergies=" + Arrays.toString(allergies) +
                '}';
    }
}
