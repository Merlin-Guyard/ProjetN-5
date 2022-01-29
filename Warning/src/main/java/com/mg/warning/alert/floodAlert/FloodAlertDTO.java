package com.mg.warning.alert.floodAlert;

import java.util.List;

public class FloodAlertDTO {

    String address;
    List<FloodAlertPersonsDTO> floodAlertPersonsDTO;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<FloodAlertPersonsDTO> getFloodAlertPersonsDTO() {
        return floodAlertPersonsDTO;
    }

    public void setFloodAlertPersonsDTO(List<FloodAlertPersonsDTO> floodAlertPersonsDTO) {
        this.floodAlertPersonsDTO = floodAlertPersonsDTO;
    }
}
