package org.example.joaopedro_jacob_at_java.exception;

public class EquipeNotFoundException extends RuntimeException {
    public EquipeNotFoundException() {super("Equipe não encontrada");}
    public EquipeNotFoundException(String message) {
        super(message);
    }
}
