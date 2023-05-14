package com.Tarefas.Gerenciador.service;

import java.util.Optional;
import org.springframework.stereotype.Service;
import com.Tarefas.Gerenciador.exceções.NotFoundException;
import com.Tarefas.Gerenciador.exceções.LoginInvalidoException;
import com.Tarefas.Gerenciador.model.Usuarios;
import com.Tarefas.Gerenciador.repository.UsuariosRepository;

@Service
public class UsuariosService {

    private UsuariosRepository usuariosRepository;

    UsuariosService(UsuariosRepository usuariosRepository) {
        this.usuariosRepository = usuariosRepository;

    }
    public Usuarios salvar(Usuarios usuario) {
        return usuariosRepository.save(usuario);
    }
    public Usuarios buscarUsuarios(Long id) {
        Optional<Usuarios> optionalUser = usuariosRepository.findById(id);
        return optionalUser.get();
    }
    public Usuarios buscarPorEmail(String email) {
        Optional<Usuarios> optionalUsuario = usuariosRepository.findByEmail(email);
        if (!optionalUsuario.isPresent()) {
            throw new NotFoundException("Email não encontrado");
        }
        return optionalUsuario.get();
    }
    public Usuarios autenticarUsuario(String email, String senha) {
        Optional<Usuarios> usuariosOptional = usuariosRepository.findByEmailAndSenha(email, senha);
        if (usuariosOptional.isPresent()) {
            Usuarios user = usuariosOptional.get();
            if (!user.getSenha().equals(senha) && user.getEmail().equals(email)) {
                throw new LoginInvalidoException("email ou senha invalidos");
            }
            return user;
        } else {
            throw new NotFoundException("email e senha não encontrados");
        }
    }
}
