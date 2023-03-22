package com.Tarefas.Gerenciador.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.Tarefas.Gerenciador.dto.AtribuirTarefaDTO;
import com.Tarefas.Gerenciador.model.Tarefas;
import com.Tarefas.Gerenciador.model.Usuarios;
import com.Tarefas.Gerenciador.repository.TarefasRepository;
import com.Tarefas.Gerenciador.repository.UsuariosRepository;

@Service
public class AtribuicaoTarefaService {

    private TarefasRepository tarefasRepository;
    private UsuariosRepository usuariosRepository;

    AtribuicaoTarefaService(TarefasRepository tarefasRepository, UsuariosRepository usuariosRepository){
        this.tarefasRepository = tarefasRepository;
        this.usuariosRepository = usuariosRepository;
    }

    public Optional<Tarefas> atribuirTarefa(AtribuirTarefaDTO atribuirTarefaDTO){
        Optional<Usuarios> optionalUsuario = usuariosRepository.findById(atribuirTarefaDTO.getId_usuario());
        Optional<Tarefas> optionalTarefas = tarefasRepository.findById(atribuirTarefaDTO.getId_tarefa());

        if(optionalUsuario.isPresent() && optionalTarefas.isPresent()){
            Usuarios usuario = optionalUsuario.get();
            Tarefas tarefa = optionalTarefas.get();
            tarefa.setUsuario(usuario);
            return Optional.of(tarefasRepository.save(tarefa));
        }
        return Optional.empty();
    }
    
}
