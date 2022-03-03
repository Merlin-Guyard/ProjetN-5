package com.mg.warning.person;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

class PersonControllerTest {


    @Autowired
    private MockMvc mvc;

    @Test
    public void testGetPerson() throws Exception
    {
        mvc.perform(MockMvcRequestBuilders.get("/person"))
                .andExpect(status().isOk());
    }

    @Test
    public void testPostPerson() throws Exception
    {
        mvc.perform( MockMvcRequestBuilders
                        .put("/person")
                        .content(asJsonString(new Person("TestFirstName","TestLastName","TestAddressTOTO","",0,"","")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void testPutPerson() throws Exception
    {
        mvc.perform( MockMvcRequestBuilders
                .put("/person")
                .content(asJsonString(new Person("TestFirstName","TestLastName","TestAddressTOTO","",0,"","")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        mvc.perform( MockMvcRequestBuilders
                        .put("/person")
                        .content(asJsonString(new Person("TestFirstName","TestLastName","TestAddressDADA","",0,"","")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.address").value("TestAddressDADA"));
    }

    @Test
    public void testDeletePerson() throws Exception
    {
        mvc.perform( MockMvcRequestBuilders
                .put("/person")
                .content(asJsonString(new Person("TestFirstName","TestLastName","TestAddressTOTO","",0,"","")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        mvc.perform( MockMvcRequestBuilders
                        .delete("/person?firstname=TestFirstName&lastname=TestLastName"))
                .andExpect(status().isAccepted());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}