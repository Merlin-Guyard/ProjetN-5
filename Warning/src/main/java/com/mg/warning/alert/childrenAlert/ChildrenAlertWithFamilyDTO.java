package com.mg.warning.alert.childrenAlert;

import java.util.ArrayList;
import java.util.List;

public class ChildrenAlertWithFamilyDTO {

    private List<ChildrenAlertDTO> children = new ArrayList<>();
    private List<FamilyAlertDTO> family = new ArrayList<>();

    public List<ChildrenAlertDTO> getChildren() {
        return children;
    }

    public void setChildren(List<ChildrenAlertDTO> children) {
        this.children = children;
    }

    public List<FamilyAlertDTO> getFamily() {
        return family;
    }

    public void setFamily(List<FamilyAlertDTO> family) {
        this.family = family;
    }




}
