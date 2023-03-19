package com.Tarefas.Gerenciador.service;

import org.springframework.stereotype.Service;

import com.Tarefas.Gerenciador.repository.UsuariosRepository;

@Service
public class UsuariosService {

    private UsuariosRepository usuariosRepository;

    UsuariosService(UsuariosRepository usuariosRepository){
        this.usuariosRepository = usuariosRepository;

    }
    
}
