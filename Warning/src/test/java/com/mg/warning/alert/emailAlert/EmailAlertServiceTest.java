package com.mg.warning.alert.emailAlert;

import com.mg.warning.firestation.FirestationRepository;
import com.mg.warning.medicalRecord.MedicalRecordRepository;
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
class EmailAlertServiceTest {

    @Mock
    private PersonRepository personRepository = mock(PersonRepository.class);

    @InjectMocks
    private EmailAlertService service = new EmailAlertService();

    @Test
    void test1() {
        List<Person> persons = new ArrayList<>();
        Person person = new Person();

        person.setCity("TestCity");
        person.setEmail("Test@email.com");
        persons.add(person);
        when(personRepository.findByCity(person.getCity()))
                .thenReturn(persons);

        List<EmailAlertDTO> resultList =  service.getEmailDTO(person.getCity());
        EmailAlertDTO result = resultList.get(0);

        assertThat(result.getEmail()).isEqualTo(person.getEmail());
    }
}