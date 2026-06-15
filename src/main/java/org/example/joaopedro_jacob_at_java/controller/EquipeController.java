package org.example.joaopedro_jacob_at_java.controller;

import org.example.joaopedro_jacob_at_java.model.DTO.EquipeDTO;
import org.example.joaopedro_jacob_at_java.service.EquipeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/equipes")
public class EquipeController {

    EquipeService equipeService;

    public EquipeController(EquipeService equipeService) {
        this.equipeService = equipeService;
    }

    @PostMapping
    public ResponseEntity<String> createEquipe(@RequestBody EquipeDTO equipeDTO){
        equipeService.registrarEquipe(equipeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Equipe registrada com sucesso!");
    }

}
