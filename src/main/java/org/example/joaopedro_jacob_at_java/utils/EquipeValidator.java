package org.example.joaopedro_jacob_at_java.utils;

import org.example.joaopedro_jacob_at_java.exception.NomeAlreadyExistsException;
import org.example.joaopedro_jacob_at_java.exception.SiglaAlreadyExistsException;
import org.example.joaopedro_jacob_at_java.model.DTO.EquipeDTO;
import org.example.joaopedro_jacob_at_java.repository.EquipeRepository;
import org.springframework.stereotype.Component;

@Component
public class EquipeValidator {

    private final EquipeRepository equipeRepository;

    public EquipeValidator(EquipeRepository equipeRepository) {
        this.equipeRepository = equipeRepository;
    }

    public void validarEquipe(EquipeDTO equipe){
        if(existeNome(equipe)){
            throw new NomeAlreadyExistsException();
        }
        if(existeSigla(equipe)){
            throw new SiglaAlreadyExistsException();
        }
    }

    public boolean existeNome(EquipeDTO equipe){return this.equipeRepository.existsByNomeAndModalidade(equipe.getNome(),equipe.getModalidade());}
    public boolean existeSigla(EquipeDTO equipe){return this.equipeRepository.existsBySiglaAndModalidade(equipe.getSigla(),equipe.getModalidade());}
}


