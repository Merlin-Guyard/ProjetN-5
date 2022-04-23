package com.mg.warning.alert.firestation;

import com.mg.warning.dto.FirestationAlertWithNbDTO;
import com.mg.warning.repository.FirestationRepository;
import com.mg.warning.repository.MedicalRecordRepository;
import com.mg.warning.repository.PersonRepository;
import com.mg.warning.service.FirestationAlertService;
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
    private FirestationAlertService service = new FirestationAlertService();

    @Test
    public void testFirestation() {
        when(firestationRepository.findByStationNumber(3))
                .thenReturn(new ArrayList<>());
        FirestationAlertWithNbDTO result = service.getFirestationAlertDTOWithSum(3);
        assertThat(result.getNbAdults()).isEqualTo(0);
        assertThat(result.getNbChildrens()).isEqualTo(0);
    }
}