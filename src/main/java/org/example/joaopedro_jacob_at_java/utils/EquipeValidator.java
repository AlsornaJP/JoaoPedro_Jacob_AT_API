package org.example.joaopedro_jacob_at_java.utils;

import org.example.joaopedro_jacob_at_java.exception.EquipeInvalidaException;
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

    public void validarCamposAtualizaveis(EquipeDTO equipe) {
        if (equipe.getCategoria() == null || equipe.getCategoria().isBlank()) throw new EquipeInvalidaException("categoria: não pode ser vazio");
        if (equipe.getCapitao() == null || equipe.getCapitao().isBlank()) throw new EquipeInvalidaException("capitao: não pode ser vazio");
        if (equipe.getTecnico() == null || equipe.getTecnico().isBlank()) throw new EquipeInvalidaException("tecnico: não pode ser vazio");
        if (equipe.getQuantidadeDeAtletas() <= 0) throw new EquipeInvalidaException("quantidadeDeAtletas: deve ser maior que zero");
        if (equipe.getStatus() == null) throw new EquipeInvalidaException("status: não pode ser nulo");
        
    }

    public boolean existeNome(EquipeDTO equipe){return this.equipeRepository.existsByNomeAndModalidade(equipe.getNome(),equipe.getModalidade());}
    public boolean existeSigla(EquipeDTO equipe){return this.equipeRepository.existsBySiglaAndModalidade(equipe.getSigla(),equipe.getModalidade());}
}


