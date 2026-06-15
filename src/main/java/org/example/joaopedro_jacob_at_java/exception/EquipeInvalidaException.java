package org.example.joaopedro_jacob_at_java.exception;

public class EquipeInvalidaException extends RuntimeException {
    public EquipeInvalidaException() { super("Dados da equipe inválidos"); }
    public EquipeInvalidaException(String message) { super(message); }
}