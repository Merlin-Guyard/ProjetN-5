package com.mg.warning.firestation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.tinylog.Logger;

import java.util.List;

@RestController
@RequestMapping("/firestation")
public class FireStationController {

    @Autowired
    private FirestationRepository firestationRepository;

    @GetMapping(value= "/GetAll")
    public List<Firestation> getAllFirestation(){
        Logger.info("/person function get all called");
        return firestationRepository.findAll();
    }

    @PostMapping(value= "")
    public void postOneFirestation(@RequestBody Firestation firestation){
        Logger.info("/person function post called for firestation : {}, number : {}", firestation.getAddress(), firestation.getStation());
        firestationRepository.save(firestation);
    }

    @PutMapping(value= "")
    public void updateOnePerson(@RequestBody Firestation firestation){
        Logger.info("/person function update called for firestation : {}, number : {}", firestation.getAddress(), firestation.getStation());
        firestationRepository.update(firestation);
    }

    @DeleteMapping(value= "")
    public void delOnePerson(@PathVariable("address") String address, @PathVariable("station") int station){
        Logger.info("/person function update called for firestation number : {}", station);
        firestationRepository.delete(address, station);
    }

}
