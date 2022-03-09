package com.mg.warning.alert.email;

import com.mg.warning.person.Person;
import com.mg.warning.person.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinylog.Logger;

import java.util.ArrayList;
import java.util.List;


@Component
public class EmailService {

    @Autowired
    private PersonRepository personRepository;

    public List<EmailDTO> getEmailDTO(String city) {

        //Get and write data
        List<EmailDTO> dtoEmailList = getEmailDTOS(city);

        Logger.info("getEmailDTO executed successfully");
        return dtoEmailList;
    }

    private List<EmailDTO> getEmailDTOS(String city) {
        Logger.debug("getting and writing emails");
        List<Person> persons = new ArrayList<>(personRepository.findByCity(city));
        List<EmailDTO> dtoEmailList = new ArrayList<>();
        for (Person person : persons) {
            EmailDTO dtoEmail = new EmailDTO();
            dtoEmail.setEmail(person.getEmail());
            dtoEmailList.add(dtoEmail);
        }
        return dtoEmailList;
    }
}

