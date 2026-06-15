package org.example.joaopedro_jacob_at_java.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.joaopedro_jacob_at_java.model.DTO.EquipeDTO;

import java.time.LocalDate;

@Entity
@Table(name = "equipes")
@Getter
@NoArgsConstructor
public class EquipeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = false, unique = true, length = 3)
    private String sigla;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Modalidade modalidade;

    @Column(nullable = false)
    private String categoria;

    @Column(nullable = false)
    private String capitao;

    @Column(nullable = false)
    private String tecnico;

    @Column(nullable = false)
    private int quantidadeDeAtletas;

    @Column
    private LocalDate fundacao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    public EquipeEntity(EquipeDTO equipeDTO) {
        this.id = equipeDTO.getId();
        this.nome = equipeDTO.getNome();
        this.sigla = equipeDTO.getSigla();
        this.modalidade = equipeDTO.getModalidade();
        this.categoria = equipeDTO.getCategoria();
        this.capitao = equipeDTO.getCapitao();
        this.tecnico = equipeDTO.getTecnico();
        this.quantidadeDeAtletas = equipeDTO.getQuantidadeDeAtletas();
        this.fundacao = equipeDTO.getFundacao();
        this.status = equipeDTO.getStatus();
    }

    public void atualizar(EquipeDTO dto) {
        this.categoria = dto.getCategoria();
        this.capitao = dto.getCapitao();
        this.tecnico = dto.getTecnico();
        this.quantidadeDeAtletas = dto.getQuantidadeDeAtletas();
        this.fundacao = dto.getFundacao();
        this.status = dto.getStatus();
    }
}
