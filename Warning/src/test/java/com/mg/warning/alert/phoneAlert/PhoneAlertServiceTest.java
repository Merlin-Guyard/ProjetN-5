package com.mg.warning.alert.phoneAlert;

import com.mg.warning.alert.PhoneAlert.PhoneAlertDTO;
import com.mg.warning.alert.PhoneAlert.PhoneAlertService;
import com.mg.warning.alert.emailAlert.EmailAlertDTO;
import com.mg.warning.alert.emailAlert.EmailAlertService;
import com.mg.warning.firestation.Firestation;
import com.mg.warning.firestation.FirestationRepository;
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

class PhoneAlertServiceTest {

    @Mock
    private PersonRepository personRepository = mock(PersonRepository.class);

    @Mock
    private FirestationRepository firestationRepository = mock((FirestationRepository.class));

    @InjectMocks
    private PhoneAlertService service = new PhoneAlertService();

    @Test
    void test1() {
        List<Person> persons = new ArrayList<>();
        Person person = new Person();
        List<Firestation> firestations = new ArrayList<>();
        Firestation firestation = new Firestation();

        person.setFirstName("Bobby");
        person.setLastName("Dupont");
        person.setAddress("TestRoad");
        person.setCity("TestCity");
        person.setZip(12345);
        person.setPhone("06 01 23 45 67");
        person.setEmail("Test@email.com");

        firestation.setAddress("TestCity");
        firestation.setStation(2);

        persons.add(person);
        firestations.add(firestation);

        when(firestationRepository.findByStationNumber(2))
                .thenReturn(firestations);

        when(personRepository.findByAddress(firestation.getAddress()))
                .thenReturn(persons);


        List<PhoneAlertDTO> resultList =  service.getPhoneDTO(2);
        PhoneAlertDTO result = resultList.get(0);

        assertThat(result.getPhone()).isEqualTo("06 01 23 45 67");
    }
}