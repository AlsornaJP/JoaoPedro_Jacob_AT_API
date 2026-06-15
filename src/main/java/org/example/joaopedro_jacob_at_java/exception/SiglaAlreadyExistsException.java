package org.example.joaopedro_jacob_at_java.exception;

public class SiglaAlreadyExistsException extends RuntimeException {
    public SiglaAlreadyExistsException() { super("Já existe um time com essa sigla.");}

    public SiglaAlreadyExistsException(String message) {
        super(message);
    }
}
