package com.mg.warning.alert;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mg.warning.controller.AlertController;
import com.mg.warning.dto.*;
import com.mg.warning.model.Person;
import com.mg.warning.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(AlertController.class)
class AlertControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FirestationAlertService firestationAlertService;

    @MockBean
    private ChildAlertService childAlertService;

    @MockBean
    private PhoneAlertService phoneAlertService;

    @MockBean
    private FireAlertService fireAlertService;

    @MockBean
    private FloodAlertService floodAlertService;

    @MockBean
    private PersonInfoAlertService personInfoAlertService;

    @MockBean
    private EmailAlertService emailAlertService;

    @Test
    void getFirestationTest() throws Exception {

        List<FirestationDTO> firestationDTOS = new ArrayList<>();
        FirestationDTO firestationDTO = new FirestationDTO();
        firestationDTO.setFirstname("Bobby");
        firestationDTO.setLastname("Dupont");
        firestationDTO.setAddress("Strawberry road");
        firestationDTO.setPhone("06 06 06 06 06");
        firestationDTOS.add(firestationDTO);

        FirestationWithNbDTO firestationWithNbDTO = new FirestationWithNbDTO();
        firestationWithNbDTO.setFirestationAlertDTOS(firestationDTOS);
        firestationWithNbDTO.setNbAdults(1);
        firestationWithNbDTO.setNbChildrens(2);

        when(firestationAlertService.getFirestationAlertDTOWithSum(42)).thenReturn(firestationWithNbDTO);

        mockMvc.perform(get("/firestation?stationNumber=42"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nbAdults", is(1)))
                .andExpect(jsonPath("$.nbChildrens", is(2)));
    }

    @Test
    void getChildTest() throws Exception {

        List<ChildrenDTO> childrenDTOS = new ArrayList<>();
        ChildrenDTO childrenDTO = new ChildrenDTO();
        childrenDTO.setFirstname("Bobby");
        childrenDTO.setLastname("Dupont");
        childrenDTO.setAge(16);
        childrenDTOS.add(childrenDTO);

        List<FamilyDTO> familyDTOS = new ArrayList<>();
        FamilyDTO familyDTO = new FamilyDTO();
        familyDTO.setFirstname("Sarah");
        familyDTO.setLastname("Dupont");
        familyDTOS.add(familyDTO);

        ChildrenWithFamilyDTO childrenWithFamilyDTO = new ChildrenWithFamilyDTO();
        childrenWithFamilyDTO.setChildren(childrenDTOS);
        childrenWithFamilyDTO.setFamily(familyDTOS);

        when(childAlertService.getChildrenWithFamilyDTO("TestAddress")).thenReturn(childrenWithFamilyDTO);

        mockMvc.perform(get("/childAlert?address=TestAddress"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.children.[0].firstname", is("Bobby")))
                .andExpect(jsonPath("$.family.[0]firstname", is("Sarah")));
    }

    @Test
    void getPhoneTest() throws Exception {

        List<PhoneDTO> phoneDTOS = new ArrayList<>();
        PhoneDTO phoneDTO = new PhoneDTO();
        phoneDTO.setPhone("06 06 06 06 06");
        phoneDTOS.add(phoneDTO);

        when(phoneAlertService.getPhoneDTO(42)).thenReturn(phoneDTOS);

        mockMvc.perform(get("/phoneAlert?firestation=42"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].phone", is("06 06 06 06 06")));
    }

    @Test
    void getFireTest() throws Exception {

        List<FirePersonDTO> firePersonDTOS = new ArrayList<>();
        FirePersonDTO firePersonDTO = new FirePersonDTO();
        firePersonDTO.setLastName("Dupont");
        firePersonDTO.setAge(16);
        firePersonDTOS.add(firePersonDTO);

        List<FireFireStationDTO> fireFireStationDTOS = new ArrayList<>();
        FireFireStationDTO fireFireStationDTO = new FireFireStationDTO();
        fireFireStationDTO.setStation(42);
        fireFireStationDTOS.add(fireFireStationDTO);

        FireDTO fireDTO = new FireDTO();
        fireDTO.setFireAlertPersonsDTO(firePersonDTOS);
        fireDTO.setFireAlertStationDTO(fireFireStationDTOS);

        when(fireAlertService.getFireDTO("TestAddress")).thenReturn(fireDTO);

        mockMvc.perform(get("/fire?address=TestAddress"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fireAlertPersonsDTO.[0].lastName", is("Dupont")))
                .andExpect(jsonPath("$.fireAlertStationDTO.[0].station", is(42)));
    }


    @Test
    void getFloodTest() throws Exception {

        List<FloodPersonsDTO> floodPersonsDTOS = new ArrayList<>();
        FloodPersonsDTO floodPersonsDTO = new FloodPersonsDTO();
        floodPersonsDTO.setLastName("Dupon");
        floodPersonsDTO.setAge(16);
        floodPersonsDTOS.add(floodPersonsDTO);

        FloodDTO floodDTO = new FloodDTO();
        floodDTO.setAddress("TestAddress");
        floodDTO.setFloodAlertPersonsDTO(floodPersonsDTOS);

        when(floodAlertService.getFloodDTO(int)).thenReturn(floodDTO);

        mockMvc.perform(get("/flood/stations?stations=42"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.address", is("TestAddress")))
                .andExpect(jsonPath("$.floodAlertPersonsDTO.[0].lastName", is("Dupont")));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}