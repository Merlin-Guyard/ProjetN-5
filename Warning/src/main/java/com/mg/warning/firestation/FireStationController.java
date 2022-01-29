package com.mg.warning.firestation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/firestation")
public class FireStationController {

    @Autowired
    private FirestationRepository firestationRepository;

    @GetMapping(value= "/GetAll")
    public List<Firestation> getAllFirestation(){
        return firestationRepository.findAll();
    }

    @PostMapping(value= "")
    public void postOneFirestation(@RequestBody Firestation firestation){
        firestationRepository.save(firestation);
    }

    @PutMapping(value= "")
    public void updateOnePerson(@RequestBody Firestation firestation){
        firestationRepository.update(firestation);
    }

    @DeleteMapping(value= "")
    public void delOnePerson(@PathVariable("address") String address, @PathVariable("station") int station){
        firestationRepository.delete(address, station);
    }

}
