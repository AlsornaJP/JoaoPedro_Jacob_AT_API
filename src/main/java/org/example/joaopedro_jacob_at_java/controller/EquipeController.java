package org.example.joaopedro_jacob_at_java.controller;

import org.example.joaopedro_jacob_at_java.model.DTO.EquipeDTO;
import org.example.joaopedro_jacob_at_java.service.EquipeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipes")
public class EquipeController {

    EquipeService equipeService;

    public EquipeController(EquipeService equipeService) {
        this.equipeService = equipeService;
    }

    @PostMapping
    public ResponseEntity<String> registrarEquipe(@RequestBody EquipeDTO equipeDTO){
        equipeService.registrarEquipe(equipeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Equipe registrada com sucesso!");
    }

    @GetMapping
    public ResponseEntity<List<EquipeDTO>> listarEquipes() {
        return ResponseEntity.ok(equipeService.listarEquipes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipeDTO> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(equipeService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EquipeDTO> atualizarEquipe(@PathVariable Integer id, @RequestBody EquipeDTO equipeDTO) {
        return ResponseEntity.ok(equipeService.atualizarEquipe(id, equipeDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEquipe(@PathVariable Integer id) {
        equipeService.deletarEquipe(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}