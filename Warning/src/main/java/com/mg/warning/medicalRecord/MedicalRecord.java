package com.mg.warning.medicalRecord;

import java.util.Arrays;

public class MedicalRecord {

    private String firstName;
    private String lastName;
    //TODO LocalDate
    private String birthdate;
    private String[] medications;
    private String[] allergies;

    public MedicalRecord() {
    }

    public MedicalRecord(String[] medication, String[] allergies) {
        this.medications = medication;
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

    public String[] getMedications() {
        return medications;
    }

    public void setMedications(String[] medications) {
        this.medications = medications;
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
                ", medication=" + Arrays.toString(medications) +
                ", allergies=" + Arrays.toString(allergies) +
                '}';
    }
}
