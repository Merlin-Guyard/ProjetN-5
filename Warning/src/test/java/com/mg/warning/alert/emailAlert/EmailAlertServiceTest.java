package com.mg.warning.alert.emailAlert;

import com.mg.warning.firestation.FirestationRepository;
import com.mg.warning.medicalRecord.MedicalRecordRepository;
import com.mg.warning.person.Person;
import com.mg.warning.person.PersonRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class EmailAlertServiceTest {

    @Mock
    private PersonRepository personRepository = mock(PersonRepository.class);

    @InjectMocks
    private EmailAlertService service = new EmailAlertService();

    @Test
    void test1() {
        List<Person> persons = new ArrayList<>();
        Person person = new Person();

        person.setFirstName("Bobby");
        person.setLastName("Dupont");
        person.setAddress("TestRoad");
        person.setCity("TestCity");
        person.setZip(12345);
        person.setPhone("06 01 23 45 67");
        person.setEmail("Test@email.com");

        persons.add(person);
        when(personRepository.findByCity("TestCity"))
                .thenReturn(persons);

        List<EmailAlertDTO> resultList =  service.getEmailDTO("TestCity");
        EmailAlertDTO result = resultList.get(0);

        assertThat(result.getEmail()).isEqualTo("Test@email.com");
    }
}