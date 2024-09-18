package com.levy.automobile.controllers.dtos;

import com.levy.automobile.controllers.enums.ModelEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;

public class AutomobileDTO {
    @NotBlank(message = "Plaque cant be blanck")
    @NotNull(message = "Plaque is a required field")
    @Size(min = 7,max = 7, message = "Plaque must have 7 characters")
    private String plaque;
    private ModelEnum model;
    private Date entryTime;
    private float levyToPay;

    public AutomobileDTO() {
    }

    public String getPlaque() {
        return plaque;
    }

    public void setPlaque(String plaque) {
        this.plaque = plaque;
    }

    public ModelEnum getModel() {
        return model;
    }

    public void setModel(ModelEnum model) {
        this.model = model;
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    public float getLevyToPay() {
        return levyToPay;
    }

    public void setLevyToPay(float levyToPay) {
        this.levyToPay = levyToPay;
    }
}
