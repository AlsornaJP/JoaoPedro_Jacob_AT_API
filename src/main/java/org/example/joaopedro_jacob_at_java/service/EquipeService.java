package org.example.joaopedro_jacob_at_java.service;

import org.example.joaopedro_jacob_at_java.model.DTO.EquipeDTO;
import org.example.joaopedro_jacob_at_java.model.EquipeEntity;
import org.example.joaopedro_jacob_at_java.repository.EquipeRepository;
import org.example.joaopedro_jacob_at_java.utils.EquipeMapper;
import org.example.joaopedro_jacob_at_java.utils.EquipeValidator;
import org.example.joaopedro_jacob_at_java.exception.EquipeNotFoundException;
import org.example.joaopedro_jacob_at_java.exception.EquipesIsEmptyException;
import org.springframework.stereotype.Service;

import java.util.List;

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
        equipeRepository.save(EquipeMapper.toEntity(equipeDTO));
    }

    public List<EquipeDTO> listarEquipes() {
        List<EquipeEntity> equipes = equipeRepository.findAll();
        if (equipes.isEmpty()){
            throw new EquipesIsEmptyException();
        }
        return equipes.stream()
                .map(EquipeMapper::toDTO)
                .toList();
    }

    public EquipeDTO buscarPorId(Integer id) {
        EquipeEntity equipe = equipeRepository.findById(id)
                .orElseThrow(EquipeNotFoundException::new);
        return EquipeMapper.toDTO(equipe);
    }

    public void deletarEquipe(Integer id) {
        equipeRepository.findById(id).orElseThrow(EquipeNotFoundException::new);
        equipeRepository.deleteById(id);
    }

    public EquipeDTO atualizarEquipe(Integer id, EquipeDTO equipeDTO) {
        equipeValidator.validarCamposAtualizaveis(equipeDTO);
        EquipeEntity equipe = equipeRepository.findById(id)
                .orElseThrow(EquipeNotFoundException::new);
        equipe.atualizar(equipeDTO);
        return EquipeMapper.toDTO(equipeRepository.save(equipe));
    }
}
