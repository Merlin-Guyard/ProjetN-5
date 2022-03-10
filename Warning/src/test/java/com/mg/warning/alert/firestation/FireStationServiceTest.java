package com.mg.warning.alert.firestation;

import com.mg.warning.firestation.FirestationRepository;
import com.mg.warning.medicalRecord.MedicalRecordRepository;
import com.mg.warning.person.PersonRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@SpringBootTest
public class FireStationServiceTest {

    @Mock
    private FirestationRepository firestationRepository = mock(FirestationRepository.class);

    @Mock
    private PersonRepository personRepository = mock(PersonRepository.class);

    @Mock
    private MedicalRecordRepository medicalRecordRepository = mock(MedicalRecordRepository.class);

    @InjectMocks
    private FireStationService service = new FireStationService();

    @Test
    public void testFirestation() {
        when(firestationRepository.findByStationNumber(3))
                .thenReturn(new ArrayList<>());
        FirestationWithNbDTO result = service.getFirestationAlertDTOWithSum(3);
        assertThat(result.getNbAdults()).isEqualTo(0);
        assertThat(result.getNbChildrens()).isEqualTo(0);
    }
}