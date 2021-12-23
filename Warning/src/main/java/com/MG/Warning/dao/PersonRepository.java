package com.MG.Warning.dao;


import com.MG.Warning.model.Persons;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonRepository {

    // CRUD

    private List<Persons> personsList = new ArrayList<>();

    public void save(Persons persons) {
        personsList.add(persons);
    }

    public List<Persons> findAll() {
        return personsList;
    }
}
