package com.levy.automobile.controllers.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ModelEnum {
    SEDAN("Sedan"),
    SUV("Sport Utility Vehicle"),
    TRUCK("Truck");

    private String displayName;

    ModelEnum(String displayName) {
        this.displayName = displayName;
    }

    @JsonValue
    public String getDisplayName() {
        return displayName;
    }
}
