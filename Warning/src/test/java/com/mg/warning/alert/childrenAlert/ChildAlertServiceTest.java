package com.mg.warning.alert.childrenAlert;

import com.mg.warning.alert.AlertService;
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
public class ChildAlertServiceTest {

        @Mock
        private PersonRepository personRepository = mock(PersonRepository.class);

        @Mock
        private MedicalRecordRepository medicalRecordRepository = mock(MedicalRecordRepository.class);

        @Mock
        private AlertService alertService = mock(AlertService.class);

        @InjectMocks
        private ChildAlertService service = new ChildAlertService();

    @Test
    void Test1() {
        List<MedicalRecord> medicalRecords = new ArrayList<>();
        MedicalRecord medicalRecord = new MedicalRecord();
        List<Person> persons = new ArrayList<>();
        Person person = new Person();


        medicalRecord.setFirstName("Bobby");
        medicalRecord.setLastName("Dupont");
        medicalRecord.setBirthdate("03/06/2005");
        medicalRecords.add(medicalRecord);
        when(medicalRecordRepository.findAll())
                .thenReturn(medicalRecords);

        person.setFirstName("Bobby");
        person.setLastName("Dupont");
        person.setAddress("TestRoad");
        persons.add(person);
        when(personRepository.findAll())
                .thenReturn(persons);
        when(alertService.getAgeFromMedicalRecords(medicalRecords, person.getFirstName(), person.getLastName()))
                .thenReturn(17);

        ChildrenAlertWithFamilyDTO result = service.getChildrenWithFamilyDTO(person.getAddress());
        List<ChildrenAlertDTO> resultChildrenlist = result.getChildren();
        ChildrenAlertDTO resultChildren = resultChildrenlist.get(0);

        assertThat(resultChildren.getFirstname()).isEqualTo(person.getFirstName());
        assertThat(resultChildren.getAge()).isEqualTo(17);
    }
}