package com.MG.Warning.dao;

import com.MG.Warning.CustomProperties;
import com.MG.Warning.model.Entities;
import com.MG.Warning.model.Persons;
import com.MG.Warning.service.ReadJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;



@Component
public class PersonsDao {

    @Autowired
    private CustomProperties props;


    // GET all
    public Iterable<Persons> getPersons() {
        String baseApiUrl = props.getApiUrl();
        String getPersonsUrl = baseApiUrl + "/person";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<Persons>> response = restTemplate.exchange(
                getPersonsUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Iterable<Persons>>() {}
        );

        return response.getBody();
    }

    //GET one
    public Persons getPerson(String firstname) {
        String baseApiUrl = props.getApiUrl();
        String getPersonsUrl = baseApiUrl + "/person";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Persons> response = restTemplate.exchange(
                getPersonsUrl,
                HttpMethod.GET,
                null,
                Persons.class
        );
        return response.getBody();
    }

    //POST
    public Persons createPerson(Persons p) {
        String baseApiUrl = props.getApiUrl();
        String getPersonsUrl = baseApiUrl + "/person";

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Persons> request = new HttpEntity<Persons>(p);
        ResponseEntity<Persons> response = restTemplate.exchange(
                getPersonsUrl,
                HttpMethod.POST,
                request,
                Persons.class
        );

        return response.getBody();
    }

    public void deletePerson(String firstname) {
    }
}
