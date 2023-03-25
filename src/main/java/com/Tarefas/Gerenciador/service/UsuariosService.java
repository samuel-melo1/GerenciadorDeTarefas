package com.Tarefas.Gerenciador.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.Tarefas.Gerenciador.model.Usuarios;
import com.Tarefas.Gerenciador.repository.UsuariosRepository;

@Service
public class UsuariosService {

    private UsuariosRepository usuariosRepository;

    UsuariosService(UsuariosRepository usuariosRepository){
        this.usuariosRepository = usuariosRepository;

    }

    public List<Usuarios> listarUsuarios(){
        return usuariosRepository.findAll();
    }

    public Usuarios salvar(Usuarios usuario){
        return usuariosRepository.save(usuario);
    }

    public Usuarios buscarPorEmail(String email){
        Optional<Usuarios> usuariosOptional = usuariosRepository.findByEmail(email);
        return usuariosOptional.get();
    }
    public Optional<Usuarios> buscarUsuarios(Long id){
        return usuariosRepository.findById(id);
    }
    
    
}
