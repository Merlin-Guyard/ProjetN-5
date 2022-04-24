package com.mg.warning.alert;

import com.mg.warning.service.*;
import com.mg.warning.service.ChildAlertService;
import com.mg.warning.controller.AlertController;
import com.mg.warning.repository.FirestationRepository;
import com.mg.warning.repository.MedicalRecordRepository;
import com.mg.warning.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = AlertController.class)
class AlertControllerTest {

    @TestConfiguration
    static class AdditionalConfig {
        @Bean
        public FirestationRepository firestationRepository() {
            return new FirestationRepository();
        }

        @Bean
        public PersonRepository personRepository() {
            return new PersonRepository();
        }

        @Bean
        public MedicalRecordRepository medicalRecordRepository() {
            return new MedicalRecordRepository();
        }

        @Bean
        public FirestationAlertService fireStationService() {
            return new FirestationAlertService();
        }

        @Bean
        public ChildAlertService childService() {
            return new ChildAlertService();
        }

        @Bean
        public PhoneAlertService phoneService() {
            return new PhoneAlertService();
        }

        @Bean
        public FireAlertService fireService() {
            return new FireAlertService();
        }

        @Bean
        public FloodAlertService floodService() {
            return new FloodAlertService();
        }

        @Bean
        public PersonInfoAlertService personInfoService() {
            return new PersonInfoAlertService();
        }

        @Bean
        public EmailAlertService emailService() {
            return new EmailAlertService();
        }
    }

    @Autowired
    private MockMvc mvc;

    @Test
    public void testGetFireStation() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/firestation?stationNumber=42"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetChildAlert() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/childAlert?address=TestAddress"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetPhone() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/phoneAlert?firestation=42"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetFire() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/fire?address=TestAddress"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetFlood() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/flood/stations?stations=42"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetPersonInfo() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/personInfo?firstName=TestFirstName&lastName=TestFirstName"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetCity() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/communityEmail?city=TestCity"))
                .andExpect(status().isOk());
    }
}