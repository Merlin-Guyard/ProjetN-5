package com.mg.warning.alert.fireAlert;

import java.util.List;

public class FireAlertDTO {

    private List<FireAlertPersonDTO> fireAlertPersonsDTO;
    private List<FireAlertStationDTO> fireAlertStationDTO;

    public List<FireAlertStationDTO> getFireAlertStationDTO() {
        return fireAlertStationDTO;
    }

    public void setFireAlertStationDTO(List<FireAlertStationDTO> fireAlertStationDTO) {
        this.fireAlertStationDTO = fireAlertStationDTO;
    }

    public List<FireAlertPersonDTO> getFireAlertPersonsDTO() {
        return fireAlertPersonsDTO;
    }

    public void setFireAlertPersonsDTO(List<FireAlertPersonDTO> fireAlertPersonsDTO) {
        this.fireAlertPersonsDTO = fireAlertPersonsDTO;
    }

}
