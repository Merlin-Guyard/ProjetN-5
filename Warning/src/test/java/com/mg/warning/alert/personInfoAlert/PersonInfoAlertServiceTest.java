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

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
        person.setAddress("TestRoad");
        person.setCity("TestCity");
        person.setZip(12345);
        person.setPhone("06 01 23 45 67");
        person.setEmail("Test@email.com");

        medicalRecord.setFirstName("Bobby");
        medicalRecord.setLastName("Dupont");
        medicalRecord.setBirthdate("03/06/1988");

        persons.add(person);
        medicalRecords.add(medicalRecord);

        when(personRepository.findByFirstAndLastName(person.getFirstName(), person.getLastName()))
                .thenReturn(persons);

        when(medicalRecordRepository.findByFirstAndLastName(medicalRecord.getFirstName(), medicalRecord.getLastName()))
                .thenReturn(medicalRecords);

        when(alertService.getAgeFromMedicalRecords(medicalRecords, medicalRecord.getFirstName(), medicalRecord.getLastName()))
                .thenReturn(34);

        List<PersonInfoAlertDTO> resultList = service.getPersonInfoDTO(person.getFirstName(), person.getLastName());
        PersonInfoAlertDTO result = resultList.get(0);

        assertThat(result.getLastName()).isEqualTo(person.getFirstName());
        assertThat(result.getAge()).isEqualTo(34);
    }
}