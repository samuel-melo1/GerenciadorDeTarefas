package com.Tarefas.Gerenciador.exceções;

public class LoginInvalidoException  extends RuntimeException{
    public LoginInvalidoException(String message){
        super(message);
    }
}
