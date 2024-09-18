package com.levy.automobile.service;

import com.levy.automobile.controllers.dtos.AutomobileDTO;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.*;

@Service
public class AutomobileService {

    private final List<AutomobileDTO> automobileDTOList = new ArrayList<>();

    public List<AutomobileDTO> getAutomobileDTOList(){
        for(AutomobileDTO automobileDTO: automobileDTOList){
            float levy = levyToPay(automobileDTO);
            automobileDTO.setLevyToPay(levy);
            int index = automobileDTOList.indexOf(automobileDTO);
            automobileDTOList.set(index, automobileDTO);
        }
        return automobileDTOList;    }

    public AutomobileDTO postAutomobileDTOList(AutomobileDTO automobileDTONew){
        Optional<AutomobileDTO> optional = findAutomobileDTO(automobileDTONew.getPlaque());
        AutomobileDTO automobileDTO = new AutomobileDTO();

        if(optional.isPresent()){
            throw new RuntimeException("This automobile already exist");
        }

        automobileDTO.setModel(automobileDTONew.getModel());
        automobileDTO.setPlaque(automobileDTONew.getPlaque());
        automobileDTO.setEntryTime(Date.from(Instant.now()));

        automobileDTOList.add(automobileDTO);

        return automobileDTO;
    }

    public void patchAutomobileDTO(AutomobileDTO automobileDTO, String plaque){
        Optional<AutomobileDTO> optional = findAutomobileDTO(plaque);
        if(optional.isEmpty()){
            throw new RuntimeException("Automobile not exist");
        }
        automobileDTO.setEntryTime(optional.get().getEntryTime());
        automobileDTO.setPlaque(plaque);
        int index = automobileDTOList.indexOf(optional.get());
        automobileDTOList.set(index, automobileDTO);
    }

    public void deleteAutomobileDTO(String plaque){
        Optional<AutomobileDTO> optional = findAutomobileDTO(plaque);
        if (optional.isEmpty()) {
            throw new RuntimeException("Automobile not exist");
        }
        automobileDTOList.remove(optional.get());
    }

    public Optional<AutomobileDTO> findAutomobileDTO(String plaque){
        return automobileDTOList.stream().filter(vehicle -> vehicle.getPlaque()
                        .equals(plaque)).findFirst();
    }

    private float levyToPay(AutomobileDTO automobileDTO){
        Date entryTime = automobileDTO.getEntryTime();
        Date departureTime = Date.from(Instant.now());

        Duration duration = Duration.between(entryTime.toInstant(), departureTime.toInstant());
        long minutes = duration.toMinutes();
        float price = minutes * 0.80f;

        if (minutes == 0) {
            price = 0.80f;
        }

        return price;
    }
}
