package org.example.joaopedro_jacob_at_java.utils;

import org.example.joaopedro_jacob_at_java.model.DTO.EquipeDTO;
import org.example.joaopedro_jacob_at_java.model.EquipeEntity;


public class EquipeMapper {
    public static EquipeEntity toEntity(EquipeDTO equipeDTO) {return new EquipeEntity(equipeDTO);}
    public static EquipeDTO toDTO(EquipeEntity entity) {return new EquipeDTO(entity);}
}
