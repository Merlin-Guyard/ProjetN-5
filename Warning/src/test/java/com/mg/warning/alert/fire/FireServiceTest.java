package com.mg.warning.alert.fire;

import com.mg.warning.firestation.Firestation;
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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class FireServiceTest {

    @Mock
    private PersonRepository personRepository = mock(PersonRepository.class);

    @Mock
    private MedicalRecordRepository medicalRecordRepository = mock(MedicalRecordRepository.class);

    @Mock
    private FirestationRepository firestationRepository = mock((FirestationRepository.class));


    @InjectMocks
    private FireService service = new FireService();

    @Test
    void testFire() {
        List<Person> persons = new ArrayList<>();
        Person person = new Person();
        List<MedicalRecord> medicalRecords = new ArrayList<>();
        MedicalRecord medicalRecord = new MedicalRecord();
        List<Firestation> firestations = new ArrayList<>();
        Firestation firestation = new Firestation();

        person.setFirstName("Bobby");
        person.setLastName("Dupont");
        person.setAddress("TestRoad");
        persons.add(person);
        when(personRepository.findByAddress(person.getAddress()))
                .thenReturn(persons);

        medicalRecord.setFirstName("Bobby");
        medicalRecord.setLastName("Dupont");
        medicalRecord.setBirthdate("03/06/2005");
        medicalRecords.add(medicalRecord);
        when(medicalRecordRepository.findAll())
                .thenReturn(medicalRecords);
        when(medicalRecord.getAgeFromMedicalRecords(medicalRecords, person.getFirstName(), person.getLastName()))
                .thenReturn(17);

        firestation.setAddress("TestRoad");
        firestation.setStation(2);
        firestations.add(firestation);
        when(firestationRepository.findByStationNumber(firestation.getStation()))
                .thenReturn(firestations);

        FireDTO result =  service.getFireDTO(firestation.getAddress());
        List<FirePersonDTO> resultPersonList = result.getFireAlertPersonsDTO();
        FirePersonDTO resultPerson = resultPersonList.get(0);

        assertThat(resultPerson.getLastName()).isEqualTo(person.getLastName());
    }

}