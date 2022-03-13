package com.mg.warning.person;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = PersonController.class)
class PersonControllerTest {

    @TestConfiguration
    static class AdditionalConfig {
        @Bean
        public PersonRepository personRepository() {
            return new PersonRepository();
        }
    }


    @Autowired
    private MockMvc mvc;

//    @MockBean
//    private PersonRepository personRepository = mock(PersonRepository.class);

    @Test
    public void testGetPerson() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/person/GetAll"))
                .andExpect(status().isOk());
    }

    @Test
    public void testPostPerson() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .post("/person")
                        .content(asJsonString(new Person("TestFirstName", "TestLastName", "TestAddressTOTO", "", 0, "", "")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testPutPerson() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .put("/person")
                        .content(asJsonString(new Person("TestFirstName", "TestLastName", "TestAddressDADA", "", 0, "", "")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeletePerson() throws Exception {
        {

            mvc.perform(MockMvcRequestBuilders
//                            .delete("/person?firstname={firstname}&lastname={lastname}","TestFirstName","TestLastName"))
                            .delete("/person?firstname=TestFirstName&lastname=testLastName"))
                    .andExpect(status().isAccepted());

//            mvc.perform(MockMvcRequestBuilders
//                            .delete("/person")
//                            .param("firstname", "TestFirstName")
//                            .param("lastname", "TestLastName")
//                            .contentType(MediaType.APPLICATION_JSON))
//            .andExpect(status().isOk());

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