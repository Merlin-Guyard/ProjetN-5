package com.mg.warning.alert;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mg.warning.alert.children.ChildService;
import com.mg.warning.alert.email.EmailService;
import com.mg.warning.alert.fire.FireService;
import com.mg.warning.alert.firestation.FirestationService;
import com.mg.warning.alert.flood.FloodService;
import com.mg.warning.alert.personInfo.PersonInfoService;
import com.mg.warning.alert.phone.PhoneService;
import com.mg.warning.alert.service.AlertController;
import com.mg.warning.firestation.FirestationRepository;
import com.mg.warning.medicalRecord.MedicalRecordRepository;
import com.mg.warning.person.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
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
        public FirestationService fireStationService() {
            return new FirestationService();
        }

        @Bean
        public ChildService childService() {
            return new ChildService();
        }

        @Bean
        public PhoneService phoneService() {
            return new PhoneService();
        }

        @Bean
        public FireService fireService() {
            return new FireService();
        }

        @Bean
        public FloodService floodService() {
            return new FloodService();
        }

        @Bean
        public PersonInfoService personInfoService() {
            return new PersonInfoService();
        }

        @Bean
        public EmailService emailService() {
            return new EmailService();
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