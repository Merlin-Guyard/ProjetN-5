package com.mg.warning.alert.personInfo;

import com.mg.warning.medicalRecord.MedicalRecord;
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
class PersonInfoServiceTest {

    @Mock
    private PersonRepository personRepository = mock(PersonRepository.class);

    @Mock
    private MedicalRecordRepository medicalRecordRepository = mock(MedicalRecordRepository.class);

    @InjectMocks
    private PersonInfoService service = new PersonInfoService();

    @Test
    void testPersonInfo() {
        List<Person> persons = new ArrayList<>();
        Person person = new Person();
        List<MedicalRecord> medicalRecords = new ArrayList<>();
        MedicalRecord medicalRecord = new MedicalRecord();

        person.setFirstName("Bobby");
        person.setLastName("Dupont");
        persons.add(person);
        when(personRepository.findByFirstAndLastName(person.getFirstName(), person.getLastName()))
                .thenReturn(persons);

        medicalRecord.setFirstName("Bobby");
        medicalRecord.setLastName("Dupont");
        medicalRecord.setBirthdate("03/06/2000");
        medicalRecords.add(medicalRecord);
        when(medicalRecordRepository.findByFirstAndLastName(medicalRecord.getFirstName(), medicalRecord.getLastName()))
                .thenReturn(medicalRecords);

        List<PersonInfoDTO> resultList = service.getPersonInfoDTO(person.getFirstName(), person.getLastName());
        PersonInfoDTO result = resultList.get(0);

        assertThat(result.getLastName()).isEqualTo(person.getLastName());
        assertThat(result.getAge()).isEqualTo(22);
    }
}