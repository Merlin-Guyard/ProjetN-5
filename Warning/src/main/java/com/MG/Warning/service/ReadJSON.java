package com.MG.Warning.service;

import com.MG.Warning.model.Entities;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import java.io.FileReader;


@Component
public class ReadJSON {

    public Entities ReadEntities(Entities e) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            return e = mapper.readValue(new FileReader("src/main/resources/data.json"), Entities.class);

        } catch (Exception ex) {
            System.out.println("Failed to read data.json");
            ex.printStackTrace();
        }

        return null;
    }
}