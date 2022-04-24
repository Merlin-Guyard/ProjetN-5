//package com.mg.warning.medicalrecord;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.mg.warning.controller.MedicalRecordController;
//import com.mg.warning.model.MedicalRecord;
//import com.mg.warning.repository.MedicalRecordRepository;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.TestConfiguration;
//import org.springframework.context.annotation.Bean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest(controllers = MedicalRecordController.class)
//class MedicalRecordControllerTest {
//
//    @TestConfiguration
//    static class AdditionalConfig {
//        @Bean
//        public MedicalRecordRepository medicalRecordRepository() {
//            return new MedicalRecordRepository();
//        }
//    }
//
//    @Autowired
//    private MockMvc mvc;
//
//    @Test
//    public void testGetMedicalRecord() throws Exception {
//        mvc.perform(MockMvcRequestBuilders.get("/medicalRecord/GetAll"))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void testPostMedicalRecord() throws Exception {
//        mvc.perform(MockMvcRequestBuilders
//                        .post("/medicalRecord")
//                        .content(asJsonString(new MedicalRecord("TestFirstName", "TestLastName", "01/01/2000", new String[]{""}, new String[]{""})))
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void testPutMedicalRecord() throws Exception {
//        mvc.perform(MockMvcRequestBuilders
//                .post("/medicalRecord")
//                .content(asJsonString(new MedicalRecord("TestFirstName", "TestLastName", "01/01/2000", new String[]{""}, new String[]{""})))
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON));
//
//        mvc.perform(MockMvcRequestBuilders
//                        .put("/medicalRecord")
//                        .content(asJsonString(new MedicalRecord("TestFirstName", "TestLastName", "01/01/2000", new String[]{""}, new String[]{""})))
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void testDeleteMedicalRecord() throws Exception {
//        {
//            mvc.perform(MockMvcRequestBuilders
//                            .delete("/medicalRecord/TestFirstName/TestLastName")
//                            .contentType(MediaType.APPLICATION_JSON))
//                    .andExpect(status().isOk());
//        }
//    }
//
//    public static String asJsonString(final Object obj) {
//        try {
//            return new ObjectMapper().writeValueAsString(obj);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//}