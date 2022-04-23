package com.mg.warning.alert.children;

import com.mg.warning.dto.ChildrenDTO;
import com.mg.warning.dto.ChildrenWithFamilyDTO;
import com.mg.warning.model.MedicalRecord;
import com.mg.warning.repository.MedicalRecordRepository;
import com.mg.warning.model.Person;
import com.mg.warning.repository.PersonRepository;
import com.mg.warning.service.ChildService;
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
        List<ChildrenDTO> resultChildrenList = result.getChildren();
        ChildrenDTO resultChildren = resultChildrenList.get(0);

        assertThat(resultChildren.getFirstname()).isEqualTo(person.getFirstName());
        assertThat(resultChildren.getAge()).isEqualTo(17);
    }
}