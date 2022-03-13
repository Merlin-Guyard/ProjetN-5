package com.mg.warning.alert.service;

import com.mg.warning.alert.children.ChildService;
import com.mg.warning.alert.email.EmailService;
import com.mg.warning.alert.fire.FireService;
import com.mg.warning.alert.firestation.FireStationService;
import com.mg.warning.alert.flood.FloodService;
import com.mg.warning.alert.personInfo.PersonInfoService;
import com.mg.warning.alert.phone.PhoneService;
import com.mg.warning.firestation.FirestationRepository;
import com.mg.warning.medicalRecord.MedicalRecordRepository;
import com.mg.warning.person.PersonRepository;
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
        public FireStationService fireStationService() {
            return new FireStationService();
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
    public void testGetPerson() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/firestation"))
                .andExpect(status().isOk());
    }
}