package com.mg.warning.alert.floodAlert;

import com.mg.warning.alert.AlertService;
import com.mg.warning.alert.fireAlert.FireAlertDTO;
import com.mg.warning.alert.fireAlert.FireAlertPersonDTO;
import com.mg.warning.alert.fireAlert.FireAlertService;
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
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class FloodAlertServiceTest {

    @Mock
    private PersonRepository personRepository = mock(PersonRepository.class);

    @Mock
    private MedicalRecordRepository medicalRecordRepository = mock(MedicalRecordRepository.class);

    @Mock
    private FirestationRepository firestationRepository = mock((FirestationRepository.class));

    @Mock
    private AlertService alertService = mock(AlertService.class);

    @InjectMocks
    private FloodAlertService service = new FloodAlertService();

    @Test
    void test1() {
        List<Person> persons = new ArrayList<>();
        Person person = new Person();
        List<MedicalRecord> medicalRecords = new ArrayList<>();
        MedicalRecord medicalRecord = new MedicalRecord();
        List<Firestation> firestations = new ArrayList<>();
        Firestation firestation = new Firestation();

        medicalRecord.setFirstName("Bobby");
        medicalRecord.setLastName("Dupont");
        medicalRecord.setBirthdate("03/06/2005");
        medicalRecords.add(medicalRecord);
        when(medicalRecordRepository.findAll())
                .thenReturn(medicalRecords);
        when(alertService.getAgeFromMedicalRecords(medicalRecords, person.getFirstName(), person.getLastName()))
                .thenReturn(21);

        firestation.setAddress("TestRoad");
        firestation.setStation(2);
        firestations.add(firestation);
        when(firestationRepository.findByStationNumber(firestation.getStation()))
                .thenReturn(firestations);
        int[] stationNumber = new int[1];
        stationNumber[0] = firestation.getStation();

        person.setFirstName("Bobby");
        person.setLastName("Dupont");
        person.setPhone("06 01 23 45 67");
        persons.add(person);
        when(personRepository.findByAddress(person.getAddress()))
                .thenReturn(persons);

        List<FloodAlertDTO> resultList =  service.getFloodDTO(stationNumber);
        FloodAlertDTO result = resultList.get(0);

        assertThat(result.getAddress()).isEqualTo(firestation.getAddress());
    }
}