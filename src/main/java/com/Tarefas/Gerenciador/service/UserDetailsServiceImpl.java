package com.Tarefas.Gerenciador.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Tarefas.Gerenciador.model.Usuarios;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UsuariosService usuariosService;
    
    UserDetailsServiceImpl(UsuariosService usuariosService){
        this.usuariosService = usuariosService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuarios usuario = usuariosService.buscarPorEmail(email);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado.");
        }
        return usuario;
    }
}