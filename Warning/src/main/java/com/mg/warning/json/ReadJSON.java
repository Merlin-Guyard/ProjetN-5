package com.mg.warning.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;


@Component
public class ReadJSON {

    Logger logger = LoggerFactory.getLogger(ReadJSON.class);

    public JsonModel readEntities() throws IOException {

        String fileName = "data.json";
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream(fileName);

        ObjectMapper mapper = new ObjectMapper();

        logger.info("JSON imported successfully");

        return mapper.readValue(is, JsonModel.class);
    }

}