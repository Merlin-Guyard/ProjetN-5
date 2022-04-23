package com.mg.warning.firestation;

import com.mg.warning.model.Firestation;
import com.mg.warning.repository.FirestationRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class FirestationRepositoryTest {

    @InjectMocks
    private FirestationRepository firestationRepository = new FirestationRepository();

    @Test
    void testFindByStationNumber() {

        Firestation firestationToAdd = new Firestation();
        firestationToAdd.setAddress("TestAddress");
        firestationToAdd.setStation(42);
        firestationRepository.save(firestationToAdd);

        List<Firestation> firestations = new ArrayList<>(firestationRepository.findByAddress("TestAddress"));
        Firestation firestationRecordToCheck = new Firestation();
        firestationRecordToCheck = firestations.get(0);
        assertThat(firestationRecordToCheck.getStation()).isEqualTo(firestationToAdd.getStation());
    }

    @Test
    void testFindByAddress() {

        Firestation firestationToAdd = new Firestation();
        firestationToAdd.setAddress("TestAddress");
        firestationToAdd.setStation(42);
        firestationRepository.save(firestationToAdd);

        List<Firestation> firestations = new ArrayList<>(firestationRepository.findByStationNumber(42));
        Firestation firestationRecordToCheck = new Firestation();
        firestationRecordToCheck = firestations.get(0);
        assertThat(firestationRecordToCheck.getAddress()).isEqualTo(firestationToAdd.getAddress());
    }
}
