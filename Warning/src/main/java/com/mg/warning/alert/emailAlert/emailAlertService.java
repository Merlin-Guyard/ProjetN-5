package com.mg.warning.alert.emailAlert;

import com.mg.warning.person.Person;
import com.mg.warning.person.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class EmailAlertService {

    @Autowired
    private PersonRepository personRepository;

    public List<EmailAlertDTO> getEmailDTO(String city) {
        List<Person> persons = new ArrayList<>();
        List<EmailAlertDTO> dtoEmailList = new ArrayList<>();

        //Get all persons from city
        persons.addAll(personRepository.findByCity(city));

        //Get only interested data
        for (Person person : persons){
            EmailAlertDTO dtoEmail = new EmailAlertDTO();
            dtoEmail.setEmail(person.getEmail());
            dtoEmailList.add(dtoEmail);
        }

        return dtoEmailList;
    }
}

