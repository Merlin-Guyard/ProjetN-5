package com.mg.warning;

import com.mg.warning.controller.FirestationController;
import com.mg.warning.model.Firestation;
import com.mg.warning.repository.FirestationRepository;
import com.mg.warning.service.FirestationService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FirestationController.class)
class TestingNewClass {

    @TestConfiguration
    static class AdditionalConfig {
        @Bean
        public FirestationRepository firestationRepository() {
            return new FirestationRepository();
        }
    }

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FirestationService firestationService;

    private Firestation firestationTest1;
    private Firestation firestationTest2;
    private Firestation firestationTest3;

    @BeforeEach
    void setUp() throws Exception {
        firestationTest1 = new Firestation("TestAddress1", 1);
        firestationTest2 = new Firestation("TestAddress2", 2);
        firestationTest3 = new Firestation("TestAddress3", 2);
    }

    @Test
    void getAllFireStationsTest() throws Exception {
        // GIVEN
        when(firestationService.getAllFirestation()).thenReturn(List.of(
                firestationTest1, firestationTest2, firestationTest3));

        // WHEN
        mockMvc.perform(MockMvcRequestBuilders.get("/firestation/GetAll"))

                // THEN
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].station", is(1)))
                .andExpect(jsonPath("$[1].station", is(2)))
                .andExpect(jsonPath("$[2].station", is(2)));

        verify(firestationService, times(1)).getAllFirestation();
    }
}

