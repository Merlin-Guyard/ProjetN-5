package com.mg.warning.alert.personInfoAlert;

import com.mg.warning.alert.AlertService;
import com.mg.warning.alert.emailAlert.EmailAlertDTO;
import com.mg.warning.alert.emailAlert.EmailAlertService;
import com.mg.warning.firestation.FirestationRepository;
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
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@SpringBootTest
class PersonInfoAlertServiceTest {

    @Mock
    private PersonRepository personRepository = mock(PersonRepository.class);

    @Mock
    private MedicalRecordRepository medicalRecordRepository = mock(MedicalRecordRepository.class);

    @Mock
    private AlertService alertService = mock(AlertService.class);

    @InjectMocks
    private PersonInfoAlertService service = new PersonInfoAlertService();

    @Test
    void test1() {
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
        medicalRecords.add(medicalRecord);
        when(medicalRecordRepository.findByFirstAndLastName(medicalRecord.getFirstName(), medicalRecord.getLastName()))
                .thenReturn(medicalRecords);
        when(alertService.getAgeFromMedicalRecords(medicalRecords, medicalRecord.getFirstName(), medicalRecord.getLastName()))
                .thenReturn(34);

        List<PersonInfoAlertDTO> resultList = service.getPersonInfoDTO(person.getFirstName(), person.getLastName());
        PersonInfoAlertDTO result = resultList.get(0);

        assertThat(result.getLastName()).isEqualTo(person.getLastName());
        assertThat(result.getAge()).isEqualTo(34);
    }
}