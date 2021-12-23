package com.MG.Warning.service;

import com.MG.Warning.dao.PersonRepository;
import com.MG.Warning.dao.PersonsDao;
import com.MG.Warning.model.Persons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {


    @Autowired
    private PersonRepository personRepository;

    public List<Persons> findAll() {
        return personRepository.findAll();
    }

//    @Autowired
//    private PersonsDao personsDao;
//
//    public Persons createPerson(){
//        return personsDao.createPerson();
//    }
//
//    public  Iterable<Persons> getPersons() {
//        return personsDao.getPersons();
//    }
//
//    public  Persons getPerson() {
//        return personsDao.getPerson();
//    }
//
//    void deletePerson(){
//        personsDao.deletePerson();
//    }
}
