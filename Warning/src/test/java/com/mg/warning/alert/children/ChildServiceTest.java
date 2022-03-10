package com.mg.warning.alert.children;

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
public class ChildServiceTest {

        @Mock
        private PersonRepository personRepository = mock(PersonRepository.class);

        @Mock
        private MedicalRecordRepository medicalRecordRepository = mock(MedicalRecordRepository.class);

        @InjectMocks
        private ChildService service = new ChildService();

    @Test
    void TestChild() {
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

        ChildrenWithFamilyDTO result = service.getChildrenWithFamilyDTO(person.getAddress());
        List<ChildrenDTO> resultChildrenlist = result.getChildren();
        ChildrenDTO resultChildren = resultChildrenlist.get(0);

        assertThat(resultChildren.getFirstname()).isEqualTo(person.getFirstName());
        assertThat(resultChildren.getAge()).isEqualTo(17);
    }
}