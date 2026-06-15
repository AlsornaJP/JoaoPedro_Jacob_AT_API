package org.example.joaopedro_jacob_at_java.exception;

import org.example.joaopedro_jacob_at_java.model.Modalidade;

public class EquipesIsEmptyException extends RuntimeException {
    public EquipesIsEmptyException() {super("Não existem equipes registradas");}
    public EquipesIsEmptyException(String message) {
        super(message);
    }
}
