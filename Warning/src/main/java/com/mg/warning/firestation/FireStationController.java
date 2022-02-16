package com.mg.warning.firestation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/firestation")
public class FireStationController {

    Logger logger = LoggerFactory.getLogger(FireStationController.class);

    @Autowired
    private FirestationRepository firestationRepository;

    @GetMapping(value = "/GetAll")
    public List<Firestation> getAllFirestation() {
        logger.info("/person function get all called");
        return firestationRepository.findAll();
    }

    @PostMapping(value = "")
    public void postOneFirestation(@RequestBody Firestation firestation) {
        logger.info("/person function post called for firestation : {}, number : {}", firestation.getAddress(), firestation.getStation());
        firestationRepository.save(firestation);
    }

    @PutMapping(value = "")
    public void updateOnePerson(@RequestBody Firestation firestation) {
        logger.info("/person function update called for firestation : {}, number : {}", firestation.getAddress(), firestation.getStation());
        firestationRepository.update(firestation);
    }

    @DeleteMapping(value = "")
    public void delOnePerson(@PathVariable("address") String address, @PathVariable("station") int station) {
        logger.info("/person function update called for firestation number : {}", station);
        firestationRepository.delete(address, station);
    }

}
