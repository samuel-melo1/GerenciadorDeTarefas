package com.Tarefas.Gerenciador.exceções;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
