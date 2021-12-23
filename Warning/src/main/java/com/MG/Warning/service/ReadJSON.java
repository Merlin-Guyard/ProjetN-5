package com.MG.Warning.service;

import com.MG.Warning.model.JsonModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;


@Component
public class ReadJSON {

    public JsonModel readEntities() throws IOException {

        String fileName = "data.json";

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream(fileName);

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(is, JsonModel.class);
    }

}