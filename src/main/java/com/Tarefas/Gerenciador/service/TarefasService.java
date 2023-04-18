package com.Tarefas.Gerenciador.service;

import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
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

    public Optional<Tarefas> criarTarefa(Tarefas tarefas) {
        Optional<Tarefas> tarefaExistenteTitulo = tarefasRepository.existsByTitulo(tarefas.getTitulo());
        if (tarefaExistenteTitulo.isPresent()) {
            System.out.println("CONFLICT: Título já em uso");
            return Optional.empty();
        }
        Optional<Tarefas> tarefaExistenteDescricao = tarefasRepository.existsByDescricao(tarefas.getDescricao());
        if (tarefaExistenteDescricao.isPresent()) {
            System.out.println("CONFLICT: Descrição já em uso");
            return Optional.empty();
        }
        return Optional.of(tarefasRepository.save(tarefas));
    }

    public Optional <List<Tarefas>> listarTarefas() {
        return Optional.of(tarefasRepository.findAll()); 

    }

    public Optional<Tarefas> buscarTarefas(Long id) {
        return tarefasRepository.findById(id);

    }

    public Optional<Tarefas> atualizarTarefas(Long id, Tarefas tarefasAtualizada) {
        Optional<Tarefas> tarefasAntiga = tarefasRepository.findById(id);

        if (!tarefasAntiga.isPresent()) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa não encontrada");
        }
        Tarefas tarefas = tarefasAntiga.get();
        tarefas.setTitulo(tarefasAtualizada.getTitulo());
        tarefas.setDescricao(tarefasAtualizada.getDescricao());
        tarefas.setData_inicio(tarefasAtualizada.getData_inicio());
        tarefas.setPrazo(tarefasAtualizada.getPrazo());
        tarefas.setStatus(tarefasAtualizada.getStatus());
        
        return Optional.of(tarefasRepository.save(tarefas));
    }

    public void excluirTarefas(Long id) {
        Optional<Tarefas> tarefas = tarefasRepository.findById(id);

        if (!tarefas.isPresent()) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa não encontrada");
        }
        tarefasRepository.delete(tarefas.get());
    }

    public Optional<Tarefas> criarTarefaUsuario(Tarefas tarefa, Long id_usuario) {
        Usuarios usuario = usuariosRepository.findById(id_usuario)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        tarefa.setUsuario(usuario);
       
        return Optional.of(tarefasRepository.save(tarefa));
    }
}