package com.mg.warning.alert.children;

import java.util.ArrayList;
import java.util.List;

public class ChildrenWithFamilyDTO {

    private String firstname;
    private String lastname;
    private int age;
    private List<FamilyDTO> family = new ArrayList<>();

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<FamilyDTO> getFamily() {
        return family;
    }

    public void setFamily(List<FamilyDTO> family) {
        this.family = family;
    }



}
