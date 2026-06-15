package org.example.joaopedro_jacob_at_java.service;

import org.example.joaopedro_jacob_at_java.model.DTO.EquipeDTO;
import org.example.joaopedro_jacob_at_java.model.EquipeEntity;
import org.example.joaopedro_jacob_at_java.repository.EquipeRepository;
import org.example.joaopedro_jacob_at_java.utils.EquipeMapper;
import org.example.joaopedro_jacob_at_java.utils.EquipeValidator;
import org.springframework.stereotype.Service;

@Service
public class EquipeService {

    private final EquipeRepository equipeRepository;
    private final EquipeValidator equipeValidator;

    public EquipeService(EquipeRepository equipeRepository, EquipeValidator equipeValidator) {
        this.equipeRepository = equipeRepository;
        this.equipeValidator = equipeValidator;
    }

    public void registrarEquipe(EquipeDTO equipeDTO) {
        equipeValidator.validarEquipe(equipeDTO);
        EquipeEntity equipeEntity = equipeRepository.save(EquipeMapper.toEntity(equipeDTO));
    }


}
