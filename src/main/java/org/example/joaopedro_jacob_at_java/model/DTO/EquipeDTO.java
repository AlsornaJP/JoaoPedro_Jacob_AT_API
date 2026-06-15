package org.example.joaopedro_jacob_at_java.model.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.joaopedro_jacob_at_java.model.EquipeEntity;
import org.example.joaopedro_jacob_at_java.model.Modalidade;
import org.example.joaopedro_jacob_at_java.model.Status;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class EquipeDTO {

    private Integer id;
    private String nome;
    private String sigla;
    private Modalidade modalidade;
    private String categoria;
    private String capitao;
    private String tecnico;
    private int quantidadeDeAtletas;
    private LocalDate fundacao;
    private Status status;

    public EquipeDTO(EquipeEntity equipeEntity) {
        this.id = equipeEntity.getId();
        this.nome = equipeEntity.getNome();
        this.sigla = equipeEntity.getSigla();
        this.modalidade =  equipeEntity.getModalidade();
        this.categoria = equipeEntity.getCategoria();
        this.tecnico = equipeEntity.getTecnico();
        this.capitao = equipeEntity.getCapitao();
        this.quantidadeDeAtletas = equipeEntity.getQuantidadeDeAtletas();
        this.fundacao = equipeEntity.getFundacao();
        this.status = equipeEntity.getStatus();
    }
}
