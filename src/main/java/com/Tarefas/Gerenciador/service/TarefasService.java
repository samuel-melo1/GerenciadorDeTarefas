package com.Tarefas.Gerenciador.service;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Tarefas.Gerenciador.exceções.NotFoundException;
import com.Tarefas.Gerenciador.model.Tarefas;
import com.Tarefas.Gerenciador.model.Usuarios;
import com.Tarefas.Gerenciador.repository.TarefasRepository;
import com.Tarefas.Gerenciador.repository.UsuariosRepository;

@Service
public class TarefasService {

    private TarefasRepository tarefasRepository;
    private UsuariosRepository usuariosRepository;

    TarefasService(TarefasRepository tarefasRepository, UsuariosRepository usuariosRepository) {
        this.tarefasRepository = tarefasRepository;
        this.usuariosRepository = usuariosRepository;
    }

    public Tarefas criarTarefa(Tarefas tarefas) {
        Optional<Tarefas> tarefasOptional = tarefasRepository.existsByTitulo(tarefas.getTitulo());
        return tarefasRepository.save(tarefas);
    }

    public Tarefas buscarTarefa(Long id) {
        Optional<Tarefas> tarefasOptional = tarefasRepository.findById(id);
        return tarefasOptional.get();

    }

    public Tarefas atualizarTarefas(Long id, Tarefas tarefasAtualizada) {
        Optional<Tarefas> tarefasAntiga = tarefasRepository.findById(id);

        if (!tarefasAntiga.isPresent()) {
            throw new NotFoundException("Tarefa não encontrada");
        }
        Tarefas tarefas = tarefasAntiga.get();
        tarefas.setTitulo(tarefasAtualizada.getTitulo());
        tarefas.setDescricao(tarefasAtualizada.getDescricao());
        tarefas.setData_inicio(tarefasAtualizada.getData_inicio());
        tarefas.setPrazo(tarefasAtualizada.getPrazo());
        tarefas.setStatus(tarefasAtualizada.getStatus());
        return tarefasRepository.save(tarefas);
    }

    public void excluirTarefas(Tarefas tarefa) {
        Optional<Tarefas> tarefas = tarefasRepository.findById(tarefa.getId_tarefa());

        if (!tarefas.isPresent()) {
            throw new NotFoundException("Tarefa não encontrada");
        }else{
            tarefasRepository.delete(tarefas.get());
        }
        
    }
    public Tarefas criarTarefaUsuario(Tarefas tarefas, Long id_usuario) {
        Usuarios usuario = usuariosRepository.findById(id_usuario)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        tarefas.setUsuarios(usuario);
        return tarefasRepository.save(tarefas);
    }
    public List<Tarefas> listarTarefasUsuarios(Long id){
        Usuarios usuario = usuariosRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return tarefasRepository.findByUsuarios(usuario);
    }
}