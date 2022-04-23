package com.mg.warning.dto;

import java.util.List;

public class FireDTO {

    private List<FirePersonDTO> fireAlertPersonsDTO;
    private List<FireFirestationDTO> fireAlertStationDTO;

    public List<FireFirestationDTO> getFireAlertStationDTO() {
        return fireAlertStationDTO;
    }

    public void setFireAlertStationDTO(List<FireFirestationDTO> fireAlertStationDTO) {
        this.fireAlertStationDTO = fireAlertStationDTO;
    }

    public List<FirePersonDTO> getFireAlertPersonsDTO() {
        return fireAlertPersonsDTO;
    }

    public void setFireAlertPersonsDTO(List<FirePersonDTO> fireAlertPersonsDTO) {
        this.fireAlertPersonsDTO = fireAlertPersonsDTO;
    }

}
