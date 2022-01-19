package com.mg.warning.dao;


import com.mg.warning.model.Firestation;
import com.mg.warning.model.MedicalRecord;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FirestationRepository {

    private List<Firestation> firestationList = new ArrayList<>();

    //GET
    public List<Firestation> findAll() {
        return firestationList;
    }

//    //PUT
//    public void update(Firestation firestation) {
//        int index = 0;
//        for (Firestation f : firestationList) {
//            if (f.getFirstName().equals(medicalRecord.getFirstName()) && f.getLastName().equals(medicalRecord.getLastName())) {
//                medicalRecordList.set(index, medicalRecord);
//                index++;
//            }
//        }
//    }

    //POST
    public void save(Firestation firestation) {
        firestationList.add(firestation);
    }

    public List<Firestation> findById(int stationNumber) {
        List<Firestation> result = new ArrayList<>();
        for(Firestation firestation: firestationList)  {
            if(firestation.getStation() == stationNumber) {
                result.add(firestation);
            }
        }
        return result;
    }

//    //DELETE

//    public void delete(String firstname, String lastname) {
//        medicalRecordList.removeIf(m -> m.getFirstName().equals(firstname) && m.getLastName().equals(lastname));
//    }
}