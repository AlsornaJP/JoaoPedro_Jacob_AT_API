package org.example.joaopedro_jacob_at_java.repository;

import org.example.joaopedro_jacob_at_java.model.EquipeEntity;
import org.example.joaopedro_jacob_at_java.model.Modalidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipeRepository extends JpaRepository<EquipeEntity, Integer> {
    boolean existsByNomeAndModalidade(String nome, Modalidade modalidade);
    boolean existsBySiglaAndModalidade(String sigla, Modalidade modalidade);
}
