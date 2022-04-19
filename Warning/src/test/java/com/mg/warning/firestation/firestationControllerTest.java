package com.mg.warning.firestation;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mg.warning.person.Person;
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

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = FireStationController.class)
class FirestationControllerTest {

    @TestConfiguration
    static class AdditionalConfig {
        @Bean
        public FirestationRepository firestationRepository() {
            return new FirestationRepository();
        }
    }

    @Autowired
    private MockMvc mvc;

    @Test
    public void testGetFirestation() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/firestation/GetAll"))
                .andExpect(status().isOk());
    }

    @Test
    public void testPostFirestation() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .post("/firestation")
                        .content(asJsonString(new Firestation("TestAddress", 42)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testPutFirestation() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .post("/firestation")
                .content(asJsonString(new Firestation("TestAddress", 42)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        mvc.perform(MockMvcRequestBuilders
                        .put("/firestation")
                        .content(asJsonString(new Firestation("TestAddress", 42)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteFirestation() throws Exception {
        {
            mvc.perform(MockMvcRequestBuilders
                            .delete("/firestation/TestAddress/42")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        }
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}