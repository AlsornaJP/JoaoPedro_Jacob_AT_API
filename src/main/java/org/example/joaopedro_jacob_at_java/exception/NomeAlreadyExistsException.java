package org.example.joaopedro_jacob_at_java.exception;

public class NomeAlreadyExistsException extends RuntimeException {
    public NomeAlreadyExistsException() {super("Já existe um time com esse nome");}

    public NomeAlreadyExistsException(String message) {
        super(message);
    }
}
