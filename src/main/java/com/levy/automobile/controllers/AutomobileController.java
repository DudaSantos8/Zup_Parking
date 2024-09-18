package com.levy.automobile.controllers;

import com.levy.automobile.controllers.dtos.AutomobileDTO;
import com.levy.automobile.service.AutomobileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/automobile")
@RestController
public class AutomobileController {

    @Autowired
    private AutomobileService automobileService;

    @GetMapping
    public  ResponseEntity<?> getAutomobileList(){
        return ResponseEntity.ok().body(automobileService.getAutomobileDTOList());
    }

    @PostMapping
    public ResponseEntity<?> postAutomobile(@RequestBody @Valid AutomobileDTO automobileDTO){
        try{
            AutomobileDTO postAutomobileDTOList= automobileService.postAutomobileDTOList(automobileDTO);
            return ResponseEntity.status(201).body(postAutomobileDTOList);
        }catch (Exception e){
            return ResponseEntity.status(400).body(Map.of("Message", e.getMessage()));
        }
    }

    @PatchMapping("/{plaque}")
    public ResponseEntity<?> patchAutomobile(@RequestBody AutomobileDTO automobileDTO, @PathVariable String plaque){
        try{
            automobileService.patchAutomobileDTO(automobileDTO, plaque);
            return ResponseEntity.status(200).body(automobileService.findAutomobileDTO(plaque));
        }catch (Exception e){
            return ResponseEntity.status(400).body(Map.of("Message", e.getMessage()));
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAutomobile(@RequestParam(name = "plaque") String plaque){
        try{
            automobileService.deleteAutomobileDTO(plaque);
            return ResponseEntity.status(204).build();
        }catch (Exception e){
            return ResponseEntity.status(400).body(Map.of("Message", e.getMessage()));
        }
    }
}
