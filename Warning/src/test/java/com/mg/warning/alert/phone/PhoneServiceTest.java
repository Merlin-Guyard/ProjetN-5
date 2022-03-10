package com.mg.warning.alert.phone;

import com.mg.warning.firestation.Firestation;
import com.mg.warning.firestation.FirestationRepository;
import com.mg.warning.person.Person;
import com.mg.warning.person.PersonRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@SpringBootTest
class PhoneServiceTest {

    @Mock
    private PersonRepository personRepository = mock(PersonRepository.class);

    @Mock
    private FirestationRepository firestationRepository = mock((FirestationRepository.class));

    @InjectMocks
    private PhoneService service = new PhoneService();

    @Test
    void testPhone() {
        List<Person> persons = new ArrayList<>();
        Person person = new Person();
        List<Firestation> firestations = new ArrayList<>();
        Firestation firestation = new Firestation();

        firestation.setAddress("TestRoad");
        firestation.setStation(2);
        firestations.add(firestation);
        when(firestationRepository.findByStationNumber(firestation.getStation()))
                .thenReturn(firestations);

        person.setAddress("TestRoad");
        person.setPhone("06 01 23 45 67");
        persons.add(person);
        when(personRepository.findByAddress(firestation.getAddress()))
                .thenReturn(persons);

        List<PhoneDTO> resultList =  service.getPhoneDTO(firestation.getStation());
        PhoneDTO result = resultList.get(0);

        assertThat(result.getPhone()).isEqualTo(person.getPhone());
    }
}