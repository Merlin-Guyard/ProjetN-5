package com.mg.warning.alert.firestation;

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
public class FireStationAlertFindPersonsAndNbServiceTest {

    @Mock
    private FirestationRepository firestationRepository = mock(FirestationRepository.class);

    @Mock
    private PersonRepository personRepository = mock(PersonRepository.class);

    @Mock
    private MedicalRecordRepository medicalRecordRepository = mock(MedicalRecordRepository.class);

    @InjectMocks
    private FireStationAlertFindPersonsAndNbService service = new FireStationAlertFindPersonsAndNbService();

    @Test
    public void test() {
        when(firestationRepository.findById(3))
                .thenReturn(new ArrayList<>());
        FirestationAlertWithNbDTO result = service.getFirestationAlertDTOWithSum(3);
        assertThat(result.getNbAdults()).isEqualTo(0);
        assertThat(result.getNbChildrens()).isEqualTo(0);
    }


    @Test
    public void test2() {
        List<Firestation> firestations = new ArrayList<>();
        Firestation firestation = new Firestation();
        firestation.setAddress("abc");
        firestations.add(firestation);
        when(firestationRepository.findById(3))
                .thenReturn(firestations);

        List<Person> persons = new ArrayList<>();
        Person person = new Person();
        person.setFirstName("AAA");
        person.setLastName("BBB");
        persons.add(person);
        when(personRepository.findByAddress("abc"))
                .thenReturn(persons);

        List<MedicalRecord> medicalRecords = new ArrayList<>();
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setBirthdate("01/01/1950");
        medicalRecords.add(medicalRecord);
        when(medicalRecordRepository.findByName("AAA", "BBB"))
                .thenReturn(medicalRecord);

        FirestationAlertWithNbDTO result = service.getFirestationAlertDTOWithSum(3);
        assertThat(result.getNbAdults()).isEqualTo(1);
        assertThat(result.getNbChildrens()).isEqualTo(0);

    }


    @Test
    public void test3() {
        List<Firestation> firestations = new ArrayList<>();
        Firestation firestation = new Firestation();
        firestation.setAddress("abc");
        firestations.add(firestation);
        when(firestationRepository.findById(3))
                .thenReturn(firestations);

        List<Person> persons = new ArrayList<>();
        Person person = new Person();
        person.setFirstName("AAA");
        person.setLastName("BBB");
        persons.add(person);
        when(personRepository.findByAddress("abc"))
                .thenReturn(persons);

        List<MedicalRecord> medicalRecords = new ArrayList<>();
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setBirthdate("01/01/2015");
        medicalRecords.add(medicalRecord);
        when(medicalRecordRepository.findByName("AAA", "BBB"))
                .thenReturn(medicalRecord);

        FirestationAlertWithNbDTO result = service.getFirestationAlertDTOWithSum(3);
        assertThat(result.getNbAdults()).isEqualTo(0);
        assertThat(result.getNbChildrens()).isEqualTo(1);

    }


    @Test
    public void test4() {
        List<Firestation> firestations = new ArrayList<>();
        Firestation firestation = new Firestation();
        firestation.setAddress("abc");
        firestations.add(firestation);
        Firestation firestation1 = new Firestation();
        firestation1.setAddress("cde");
        firestations.add(firestation1);
        when(firestationRepository.findById(3))
                .thenReturn(firestations);

        List<Person> persons = new ArrayList<>();
        Person person = new Person();
        person.setFirstName("AAA");
        person.setLastName("BBB");
        persons.add(person);
        when(personRepository.findByAddress("abc"))
                .thenReturn(persons);

        List<Person> persons1 = new ArrayList<>();
        Person person2 = new Person();
        person2.setFirstName("ZZZ");
        person2.setLastName("YYY");
        persons1.add(person2);
        when(personRepository.findByAddress("cde"))
                .thenReturn(persons1);

        List<MedicalRecord> medicalRecords = new ArrayList<>();
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setBirthdate("01/01/2015");
        medicalRecords.add(medicalRecord);
        when(medicalRecordRepository.findByName("AAA", "BBB"))
                .thenReturn(medicalRecord);

        List<MedicalRecord> medicalRecords2 = new ArrayList<>();
        MedicalRecord medicalRecord2 = new MedicalRecord();
        medicalRecord2.setBirthdate("01/01/2016");
        medicalRecords2.add(medicalRecord2);
        when(medicalRecordRepository.findByName("ZZZ", "YYY"))
                .thenReturn(medicalRecord2);

        FirestationAlertWithNbDTO result = service.getFirestationAlertDTOWithSum(3);
        assertThat(result.getNbAdults()).isEqualTo(0);
        assertThat(result.getNbChildrens()).isEqualTo(2);

    }
}